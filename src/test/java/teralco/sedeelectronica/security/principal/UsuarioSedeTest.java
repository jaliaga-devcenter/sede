package teralco.sedeelectronica.security.principal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsuarioSedeTest {
	@Test
	public void setUser() {
		String doc = "doc";
		String nombre = "nombre";
		String ticket = "Ticket";
		UsuarioSede user = new UsuarioSede();
		user.setDocumento(doc);
		user.setNombreCompleto(nombre);
		user.setTicket(ticket);

		assertEquals(doc, user.getDocumento());
		assertEquals(nombre, user.getNombreCompleto());
		assertEquals(ticket, user.getTicket());

	}
}
