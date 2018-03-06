package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContactoTest {

	@Test
	public void testAdjudicacion() {
		// DECLARE VARIABLES
		String comentario = "esto es un comentario cualquiera";
		String email = "correo@dominio.es";
		String nombre = "nombre contacto";
		// ARRANGE
		Contacto uncontacto = new Contacto();

		uncontacto.setNombre(nombre);
		uncontacto.setEmail(email);
		uncontacto.setComentario(comentario);

		// ASSERT
		assertEquals(nombre, uncontacto.getNombre());
		assertEquals(email, uncontacto.getEmail());
		assertEquals(comentario, uncontacto.getComentario());

	}
}
