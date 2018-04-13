package teralco.sedeelectronica.gexflow.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import teralco.sedeelectronica.gexflow.dto.IconoDTO;

public class IconoDTOTest {

	@Test
	public void testIconoDTO() {

		String pNombre = "nom";
		String pExt = ".dev";

		// ARRANGE
		IconoDTO icon = new IconoDTO(pNombre, pExt, null);
		// ASSERT

		assertEquals(pNombre, icon.getNombre());
		assertEquals(pExt, icon.getExtension());
		assertNull(icon.getFichero());

	}

}
