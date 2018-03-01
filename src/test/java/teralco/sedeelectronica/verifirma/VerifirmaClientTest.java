package teralco.sedeelectronica.verifirma;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class VerifirmaClientTest {

	@Autowired
	private VerifirmaClient cliente;

	@Test
	public void testVerifirmaClient() throws ServiceException, IOException {
//		ARRANGE
		int idEntidad = 0;
		String cvd = "zEKrL5P5Ig-yqt+Kzr5f0ow";
		
//		ACT
		File obtenerDocumentoPorCvd = this.cliente.obtenerDocumentoPorCvd(idEntidad, cvd);
		
//		ASSERT
		assertNotNull(obtenerDocumentoPorCvd);
	}
}
