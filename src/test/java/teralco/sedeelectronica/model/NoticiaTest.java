package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NoticiaTest {
	@Test
	public void testNoticia() {
		// DECLARE VARIABLES
		String titulo = "pedazo de título";
		String desc = "cualquier descripción";
		// ARRANGE
		Noticia news = new Noticia();

		news.setTitulo(titulo);
		news.setDescripcion(desc);
		// ASSERT
		assertEquals(desc, news.getDescripcion());
		assertEquals(titulo, news.getTitulo());
	}
}
