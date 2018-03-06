package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParadaTest {
	@Test
	public void testParada() {
		// DECLARE VARIABLES
		String desc = "cualquier descripción";
		// ARRANGE
		Parada parada = new Parada();
		parada.setDescripcion(desc);

		// ASSERT
		assertEquals(desc, parada.getDescripcion());
	}
}
