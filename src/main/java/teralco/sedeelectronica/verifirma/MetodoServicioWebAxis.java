package teralco.sedeelectronica.verifirma;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.Handler;
import org.apache.axis.client.Call;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.TypeMapping;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.JAFDataHandlerDeserializerFactory;
import org.apache.axis.encoding.ser.JAFDataHandlerSerializerFactory;
import org.springframework.util.StringUtils;

public class MetodoServicioWebAxis {

	private Call call;
	private OperationDesc operationDesc;
	private List<Object> parametrosEntrada = new ArrayList<Object>();
	private ServicioWebAxisUtils servicio;

	public static MetodoServicioWebAxis crearMetodoServicioWeb(String url, String nombreServicio, String nombreMetodo) throws ServiceException {
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

	protected MetodoServicioWebAxis(ServicioWebAxisUtils _servicio, String direccionServicio, String nombreMetodo) throws ServiceException {
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

	public void setClientHandlers(Handler reqHandler, Handler respHandler) {
		this.call.setClientHandlers(reqHandler, respHandler);
	}

	public void setProperty(String prop, String value) {
		this.call.setProperty(prop, value);
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
		if (qName == null) {
			if (valor.getClass() == byte[].class) {
				qName = XMLType.SOAP_BASE64;
			}
		}
		if (qName == null) {
			throw new IllegalArgumentException("valor no pertenece a una clase permitida para invocar al servicio web");
		}

		ParameterDesc param = new ParameterDesc(new javax.xml.namespace.QName("", nombre), org.apache.axis.description.ParameterDesc.IN, qName, valor.getClass(), false, false);

		this.operationDesc.addParameter(param);
		this.parametrosEntrada.add(valor);
	}

	public void addParametroEntrada(String nombre, String nombreBean, Object valor) {
		if (StringUtils.isEmpty(nombre)) {
			throw new IllegalArgumentException("nombre no puede ser nulo");
		}

		QName qName = new javax.xml.namespace.QName("", nombreBean);

		ParameterDesc param = new ParameterDesc(new javax.xml.namespace.QName("", nombre), org.apache.axis.description.ParameterDesc.IN, qName, valor.getClass(), false, false);

		this.operationDesc.addParameter(param);
		this.parametrosEntrada.add(valor);
	}

	public void addAdjuntoEntrada(String nombre, String rutaFichero) {
		DataHandler dh = MetodoServicioWebAxis.crearDataHandler2(rutaFichero);
		this.addDataHandlerEntrada(nombre, dh);
	}

	public void setTipoSalidaFichero() {
		this.operationDesc.setReturnClass(DataHandler.class);
	}

	private void addDataHandlerEntrada(String nombre, DataHandler dh) {
		QName qName = new QName("", nombre);

		this.call.registerTypeMapping(dh.getClass(), qName, JAFDataHandlerSerializerFactory.class, JAFDataHandlerDeserializerFactory.class);

		ParameterDesc param = new ParameterDesc(qName, ParameterDesc.IN, qName, dh.getClass(), false, false);

		this.operationDesc.addParameter(param);
		this.parametrosEntrada.add(dh);
	}

	/**
	 * @deprecated si el tipo de salida es simple, hay que especificar el qname adecuado como segundo par�metro. Este qname se puede encontrar en
	 *             org.apache.axis.Constants.XSD_*
	 */
	@Deprecated
	public void setTipoSalida2(Class<?> claseJava) {
		this.operationDesc.setReturnClass(claseJava);
	}

	public void setTipoSalida(Class<?> claseJava) {
		QName qName = MetodoServicioWebAxis.getQNameFromJavaClass(claseJava);
		this.setTipoSalida(claseJava, qName);
	}

	public void setTipoSalida(Class<?> claseJava, String nombreClase) {
		Class<?> cls = claseJava;
		QName qName = this.registrarBean(cls, nombreClase);
		this.setTipoSalida(claseJava, qName);
	}

	private void setTipoSalida(Class<?> claseJava, QName qName) {
		this.operationDesc.setReturnClass(claseJava);
		this.operationDesc.setReturnType(qName);
	}

	public QName registrarBean(Class<?> cls, String nombreClase) {
		return this.registrarBeanConNombreUri(cls, nombreClase, "");
	}

	public QName registrarBeanConNombreUri(Class<?> cls, String nombreClase, String uri) {
		QName qName = new javax.xml.namespace.QName(uri, nombreClase);
		this.servicio.cachedSerQNames.add(qName);
		this.servicio.cachedSerClasses.add(cls);
		this.servicio.cachedSerFactories.add(BeanSerializerFactory.class);
		this.servicio.cachedDeserFactories.add(BeanDeserializerFactory.class);

		this.call.registerTypeMapping(cls, qName, BeanSerializerFactory.class, BeanDeserializerFactory.class, false);
		return qName;
	}

	public void setTipoSalida(Class<?> claseJava, Class<?> claseArray, String nombreClase, String nombreArray) {
		QName qName = new javax.xml.namespace.QName("", nombreClase);
		this.servicio.cachedSerQNames.add(qName);
		Class<?> cls = claseJava;
		this.servicio.cachedSerClasses.add(cls);
		this.servicio.cachedSerFactories.add(BeanSerializerFactory.class);
		this.servicio.cachedDeserFactories.add(BeanDeserializerFactory.class);

		qName = new javax.xml.namespace.QName("", nombreArray);
		this.servicio.cachedSerQNames.add(qName);
		cls = claseArray;
		this.servicio.cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("", nombreClase);
		QName qName2 = null;
		this.servicio.cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		this.servicio.cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		this.call.registerTypeMapping(cls, qName, org.apache.axis.encoding.ser.ArraySerializerFactory.class, org.apache.axis.encoding.ser.ArrayDeserializerFactory.class,
			false);
		this.servicio.registrarSerializadores();

		TypeMapping tm = this.call.getTypeMapping();
		this.operationDesc.setReturnType(tm.getTypeQName(cls));
		this.operationDesc.setReturnClass(claseArray);
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
		if (clase == Double.class) {
			return XMLType.XSD_DOUBLE;
		}
		if (clase == Long.class) {
			return XMLType.XSD_LONG;
		}
		if (clase == Short.class) {
			return XMLType.XSD_SHORT;
		}
		if (clase == Float.class) {
			return XMLType.XSD_FLOAT;
		}
		if (clase == Byte.class) {
			return XMLType.XSD_BYTE;
		}
		if (clase == Boolean.class) {
			return XMLType.XSD_BOOLEAN;
		}

		return null;
	}
	
	public static DataHandler crearDataHandler2(String ruta) {
		return new DataHandler(new FileDataSource(new File(ruta)));
	}

}
