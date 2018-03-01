package teralco.sedeelectronica.verifirma;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.xml.rpc.ServiceException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VerifirmaClient {

	@Value("${verifirma.url}")
	private String backend;
	
	@Value("${verifirma.user}")
	private String usuario;

	@Value("${verifirma.password}")
	private String password;
	
	public File obtenerDocumentoPorCvd(Integer idEntidad, String cvd) throws ServiceException, IOException {

		MetodoServicioWebAxis metodo = MetodoServicioWebAxis.crearMetodoServicioWeb(
				this.backend + "/gexflow/services/EXPEDIENTES.v1_0_0", "EXPEDIENTES.v1_0_0",
				"obtenerDocumentoPorCvd");

		metodo.addParametroEntrada("usuario", this.usuario);
		metodo.addParametroEntrada("clave", this.password);
		metodo.addParametroEntrada("entidad", idEntidad);
		metodo.addParametroEntrada("cvd", cvd);
		metodo.setTipoSalidaFichero();
		metodo.setUser(this.usuario);
		metodo.setPassword(this.password);

		DataHandler data = (DataHandler) metodo.invocar();
		File tempFile = File.createTempFile("verifirma", ".pdf");
		FileUtils.copyInputStreamToFile(data.getDataSource().getInputStream(), tempFile);
		return tempFile;
	}
	
	public void setBackend(String pBackend) {
		this.backend = pBackend;
	}

	public void setUsuario(String pUsuario) {
		this.usuario = pUsuario;
	}

	public void setPassword(String pPassword) {
		this.password = pPassword;
	}
	
}
