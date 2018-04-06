package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class LenguajeTest {
	@Test
	public void testLenguaje() {
		String pIdioma = "castellano";
		String pLang = "es";

		Lenguaje len = new Lenguaje(pLang, pIdioma);

		assertEquals(len.getCodigo(), pLang);
		assertEquals(len.getIdioma(), pIdioma);

		/* set test */
		len.setCodigo("");
		len.setIdioma("");

		assertNotEquals(len.getCodigo(), pLang);
		assertNotEquals(len.getIdioma(), pIdioma);

	}
}
