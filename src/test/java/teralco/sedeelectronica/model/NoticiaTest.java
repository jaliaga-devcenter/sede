package teralco.sedeelectronica.model;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class NoticiaTest {
	@Test
	public void testNoticia() {
		// DECLARE VARIABLES
		// ARRANGE
		Noticia news = new Noticia();

		// ASSERT
		assertNotEquals(null, news);
	}
}
