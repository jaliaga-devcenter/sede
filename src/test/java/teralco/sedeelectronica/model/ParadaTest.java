package teralco.sedeelectronica.model;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ParadaTest {
	@Test
	public void testParada() {
		// ARRANGE
		Parada parada = new Parada();

		// ASSERT
		assertNotEquals(null, parada);
	}
}
