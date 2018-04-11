package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class NormativaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAnuncio() {
		String pArticulo = "art 12354";
		String pNorma = "esto es una norma";
		String pTexto = "textoooo";
		String pLang = "es";
		// DECLARE VARIABLES

		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setUrl(pUrl);
		norma.setTraducciones(new ArrayList<>());
		norma.getTraducciones().add(new NormativaLenguaje(pLang));
		norma.getTraducciones().get(0).setArticulo(pArticulo);
		norma.getTraducciones().get(0).setNorma(pNorma);
		norma.getTraducciones().get(0).setTexto(pTexto);
		// ASSERT
		assertEquals(pUrl, norma.getUrl());
		assertEquals(norma.getTraducciones().get(0).getArticulo(), pArticulo);
		assertEquals(norma.getTraducciones().get(0).getNorma(), pNorma);
		assertEquals(norma.getTraducciones().get(0).getTexto(), pTexto);
		assertEquals(norma.getTraducciones().get(0).getIdioma(), pLang);
	}
}
