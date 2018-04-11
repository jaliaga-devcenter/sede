package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ParadaTest {
	@Test
	public void testParada() {
		String pDesc = "desc";
		String pLang = "es";
		// ARRANGE
		Parada parada = new Parada();

		parada.setTraducciones(new ArrayList<>());
		parada.getTraducciones().add(new ParadaLenguaje(pLang));
		parada.getTraducciones().get(0).setDescripcion(pDesc);

		// ASSERT
		assertNotEquals(null, parada);
		assertEquals(parada.getTraducciones().get(0).getIdioma(), pLang);
		assertEquals(parada.getTraducciones().get(0).getDescripcion(), pDesc);
	}
}
