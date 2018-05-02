package teralco.sedeelectronica.auth.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DiaFestivoDTOTest {
	@Test
	public void testDiaFestivoDTO() {
		String pDia = "14";
		String pDesc = "desc";
		String pTipo = "tipo";

		// ARRANGE
		DiaFestivoDTO dia = new DiaFestivoDTO(pDia, pDesc, pTipo);

		// ASSERT

		assertEquals(pDia, dia.getDia());
		assertEquals(pDesc, dia.getDescripcion());
		assertEquals(pTipo, dia.getTipo());
	}
}
