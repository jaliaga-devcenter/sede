package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class NoticiaTest {
	@Test
	public void testNoticia() {
		String pDesc = "desc";
		String pTitulo = "tituuulooo";
		String pLang = "es";
		// DECLARE VARIABLES
		// ARRANGE
		Noticia news = new Noticia();
		news.setTraducciones(new ArrayList<>());
		news.getTraducciones().add(new NoticiaLenguaje(pLang));
		news.getTraducciones().get(0).setTitulo(pTitulo);
		news.getTraducciones().get(0).setDescripcion(pDesc);
		// ASSERT
		assertNotEquals(null, news);
		assertEquals(news.getTraducciones().get(0).getDescripcion(), pDesc);
		assertEquals(news.getTraducciones().get(0).getTitulo(), pTitulo);
		assertEquals(news.getTraducciones().get(0).getIdioma(), pLang);
	}
}
