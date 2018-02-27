package teralco.sedeelectronica.verifirma;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

public class ServicioWebAxisUtils extends org.apache.axis.client.Stub {

	public List<Class<?>> cachedSerClasses = new ArrayList<Class<?>>();
	public List<QName> cachedSerQNames = new ArrayList<QName>();
	public List cachedSerFactories = new ArrayList();
	public List cachedDeserFactories = new ArrayList();
	private String direccionServicio;

	public ServicioWebAxisUtils(String _direccionServicio, URL endpointURL) {
		this.service = new org.apache.axis.client.Service();

		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");

		this.direccionServicio = _direccionServicio;
		this.cachedEndpoint = endpointURL;
	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call call = super._createCall();
			if (super.maintainSessionSet) {
				call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				call.setProperty(key, super.cachedProperties.get(key));
			}

			return call;
		} catch (Exception _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public MetodoServicioWebAxis getMetodo(String nombre) throws ServiceException {
		if (super.cachedEndpoint == null) {
			throw new ServiceException("No hay cacheEndpoint");
		}

		return new MetodoServicioWebAxis(this, this.direccionServicio, nombre);
	}
}
