package teralco.sedeelectronica.gexflow.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import teralco.sedeelectronica.gexflow.dto.SubcategoriaDTO;

public class SubcategoriaDTOTest {
	@Test
	public void testSubcategoriaDTO() {
		Integer id = 15;
		String pNombre = "nom";
		String pDesc = "desc";

		// ARRANGE
		SubcategoriaDTO sub = new SubcategoriaDTO(id, pNombre, pDesc);
		// ASSERT

		assertEquals(id, sub.getIdSubcategoria());
		assertEquals(pNombre, sub.getNombre());
		assertEquals(pDesc, sub.getDescripcion());

	}
}
