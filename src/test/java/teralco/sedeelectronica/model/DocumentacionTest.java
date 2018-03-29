package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class DocumentacionTest {

	@Test
	public void testAdjudicacion() {
		// DECLARE VARIABLES
		Fichero file = new Fichero();
		file.setTamanyo(512.24);

		// ARRANGE
		Documentacion doc = new Documentacion();
		doc.setEstado(Estado.APERTURA);
		doc.setFichero(file);

		// ASSERT
		assertEquals(file, doc.getFichero());
		assertNotEquals(Estado.ADJUDICACION, doc.getEstado());

	}
}
