package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BaseLenguajeTest {

	@Test
	public void testBaseLenguaje() {
		BaseLanguage bm = new BaseLanguage();
		bm.setIdioma("es");
		String lang = "es";
		assertEquals(lang, bm.getIdioma());
	}
}
