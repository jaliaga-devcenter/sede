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

	public void registrarSerializadores() {
		synchronized (this) {
			if (this.firstCall()) {
				// must set encoding style before registering serializers
				this._call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
				this._call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
				for (int i = 0; i < this.cachedSerFactories.size(); ++i) {
					this.registerTypeMapping(i);
				}
			}
		}
	}

	private void registerTypeMapping(int i) {
		Class<?> cls = this.cachedSerClasses.get(i);
		javax.xml.namespace.QName qName = this.cachedSerQNames.get(i);
		java.lang.Object x = this.cachedSerFactories.get(i);

		if (x instanceof Class<?>) {
			Class<?> sf = (Class<?>) this.cachedSerFactories.get(i);
			Class<?> df = (Class<?>) this.cachedDeserFactories.get(i);
			this._call.registerTypeMapping(cls, qName, sf, df, false);
			return;
		}
		if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
			org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) this.cachedSerFactories.get(i);
			org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) this.cachedDeserFactories.get(i);
			this._call.registerTypeMapping(cls, qName, sf, df, false);
		}
	}

	public MetodoServicioWebAxis getMetodo(String nombre) throws ServiceException {
		if (super.cachedEndpoint == null) {
			throw new ServiceException("No hay cacheEndpoint");
		}

		return new MetodoServicioWebAxis(this, this.direccionServicio, nombre);
	}
}
