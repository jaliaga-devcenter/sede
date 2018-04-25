package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

public class DocumentacionTest {

	@Test
	public void testAdjudicacion() {
		String pDesc = "desc";
		String pLang = "es";
		// DECLARE VARIABLES
		Fichero file = new Fichero();
		file.setTamanyo(512.24);

		// ARRANGE
		Documentacion doc = new Documentacion();
		doc.setEstado((short) Estado.APERTURA.ordinal());
		doc.setFichero(file);
		doc.setFileToUpload(null);

		doc.setTraducciones(new ArrayList<>());
		doc.getTraducciones().add(new DocumentacionLenguaje(pLang));
		doc.getTraducciones().get(0).setDescripcion(pDesc);

		// ASSERT
		assertEquals(file, doc.getFichero());
		assertNotEquals(Estado.ADJUDICACION, doc.getEstado());
		assertNull(doc.getFileToUpload());
		assertEquals(doc.getTraducciones().get(0).getIdioma(), pLang);
		assertEquals(doc.getTraducciones().get(0).getDescripcion(), pDesc);
	}
}
