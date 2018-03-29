package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NormativaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAnuncio() {
		// DECLARE VARIABLES

		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setUrl(pUrl);

		// ASSERT
		assertEquals(pUrl, norma.getUrl());

	}
}
