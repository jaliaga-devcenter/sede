package teralco.sedeelectronica.verifirma;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.springframework.util.StringUtils;

public class MetodoServicioWebAxis {

	private Call call;
	private OperationDesc operationDesc;
	private List<Object> parametrosEntrada = new ArrayList<Object>();
	private ServicioWebAxisUtils servicio;

	public static MetodoServicioWebAxis crearMetodoServicioWeb(String url, String nombreServicio, String nombreMetodo)
			throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(url);
		} catch (MalformedURLException e) {
			throw new ServiceException(e);
		}
		ServicioWebAxisUtils stub = new ServicioWebAxisUtils(url, endpoint);
		stub.setPortName(nombreServicio);
		return stub.getMetodo(nombreMetodo);
	}

	protected MetodoServicioWebAxis(ServicioWebAxisUtils _servicio, String direccionServicio, String nombreMetodo)
			throws ServiceException {
		this.servicio = _servicio;

		QName operationName = new QName(direccionServicio, nombreMetodo);
		this.operationDesc = new OperationDesc();
		this.operationDesc.setName(nombreMetodo);
		this.operationDesc.setStyle(org.apache.axis.constants.Style.RPC);
		this.operationDesc.setUse(org.apache.axis.constants.Use.ENCODED);

		try {
			this.call = this.servicio.createCall();
		} catch (RemoteException e) {
			throw new ServiceException(e);
		}

		this.call.setUseSOAPAction(true);
		this.call.setSOAPActionURI("");
		this.call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		this.call.setOperationName(operationName);

	}

	public void setUser(String user) {
		this.call.setProperty(javax.xml.rpc.Call.USERNAME_PROPERTY, user);
	}

	public void setPassword(String pass) {
		this.call.setProperty(javax.xml.rpc.Call.PASSWORD_PROPERTY, pass);
	}

	public void addParametroEntrada(String nombre, Object valor) {
		if (StringUtils.isEmpty(nombre)) {
			throw new IllegalArgumentException("nombre no puede ser nulo");
		}

		QName qName = MetodoServicioWebAxis.getQNameFromJavaClass(valor.getClass());
		if (qName == null && valor.getClass() == byte[].class) {
			qName = XMLType.SOAP_BASE64;

		}
		if (qName == null) {
			throw new IllegalArgumentException("valor no pertenece a una clase permitida para invocar al servicio web");
		}

		ParameterDesc param = new ParameterDesc(new javax.xml.namespace.QName("", nombre),
				org.apache.axis.description.ParameterDesc.IN, qName, valor.getClass(), false, false);

		this.operationDesc.addParameter(param);
		this.parametrosEntrada.add(valor);
	}

	public void setTipoSalidaFichero() {
		this.operationDesc.setReturnClass(DataHandler.class);
	}

	public Object invocar() throws RemoteException {
		this.call.setOperation(this.operationDesc);
		return this.call.invoke(this.parametrosEntrada.toArray());
	}

	public static QName getQNameFromJavaClass(Class<?> clase) {
		if (clase == String.class) {
			return XMLType.XSD_STRING;
		}
		if (clase == Integer.class) {
			return XMLType.XSD_INTEGER;
		}

		return null;
	}

}
