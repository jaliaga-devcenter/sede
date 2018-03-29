package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NormativaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAnuncio() {
		// DECLARE VARIABLES
		String pArticulo = "nº 145";
		String pNorma = "una norma";
		String pTexto = "el texto";
		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setArticulo(pArticulo);
		norma.setNorma(pNorma);
		norma.setTexto(pTexto);
		norma.setUrl(pUrl);

		// ASSERT
		assertEquals(pArticulo, norma.getArticulo());
		assertEquals(pNorma, norma.getNorma());
		assertEquals(pUrl, norma.getUrl());
		assertEquals(pTexto, norma.getTexto());
	}
}
