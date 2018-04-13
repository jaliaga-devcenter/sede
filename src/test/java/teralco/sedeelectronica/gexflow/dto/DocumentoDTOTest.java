package teralco.sedeelectronica.gexflow.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import teralco.sedeelectronica.gexflow.dto.DocumentoDTO;

public class DocumentoDTOTest {

	@Test
	public void testDocumentoDTO() {
		Integer id = 14;
		String pNombre = "nom";
		String pDesc = "desc";
		String pEnlace = "www.google.es";

		// ARRANGE
		DocumentoDTO doc = new DocumentoDTO(id, pNombre, pDesc, pEnlace);
		// ASSERT

		assertEquals(id, doc.getIdDocumento());
		assertEquals(pDesc, doc.getTipoDocumento());
		assertEquals(pNombre, doc.getNombre());
		assertEquals(pEnlace, doc.getEnlace());

	}

}
