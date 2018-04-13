package terealco.sedeelectronica.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.utils.FicheroUtils;

public class FicheroUtilsTest {

	@Test
	public void readString() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file.pdf", "orig.pdf", null, "bar".getBytes());

		Fichero out = FicheroUtils.convertirFichero(file);
		assertNotNull(out);

	}
}
