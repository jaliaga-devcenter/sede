package teralco.sedeelectronica.gexflow.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;

public class CategoriaDTOTest {

	@Test
	public void testCategoriaDTO() {
		Integer id = 14;
		String pNombre = "nom";
		String pDesc = "desc";

		// ARRANGE
		CategoriaDTO cat = new CategoriaDTO(id, pNombre, pDesc);
		cat.setSubcategorias(null);
		// ASSERT

		assertEquals(id, cat.getIdCategoria());
		assertEquals(pDesc, cat.getDescripcion());
		assertEquals(pNombre, cat.getNombre());
		assertNull(cat.getSubcategorias());
	}

}
