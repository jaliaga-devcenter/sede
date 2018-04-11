package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class FicheroTest {
	@Test
	public void testFichero() {
		// DECLARE VARIABLES
		String archivo = "cualquier.pdf";
		String uuid = "0123456789abcdefghijklmnopqrstuvwxyz";
		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		file.setTipo(Tipo.PDF);
		file.setNombreOriginal(archivo);
		file.setUuid(uuid);

		// ASSERT
		assertEquals(archivo, file.getNombreOriginal());
		assertNotEquals(Tipo.URL, file.getTipo());
		assertEquals(uuid, file.getUuid());
		assertEquals((Double) 512.24, file.getTamanyo());
	}
}
