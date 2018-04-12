package teralco.sedeelectronica.gexflow.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import teralco.sedeelectronica.gexflow.dto.ServicioDTO;

public class ServicioDTOTest {
	@Test
	public void testServicioDTO() {
		Integer id = 15;
		String pNombre = "nom";
		String pDesc = "desc";
		String pDen = "denominacion";
		String pDoc = "doc";
		String pTipo = "tipo";
		String pTexto = "texto";
		String pTextoOnline = "denominacion";
		String pTextoTlf = "textoo";

		String pDest = "destinatarios";
		String pInicio = "inicio";
		String pCoste = "coste";
		String pPlazo = "plazo";
		String pOrgano = "organo";
		String pRecursos = "recursos";
		String pFundamento = "fundamento";
		String pPreguntas = "pregunta";
		String pContacto = "contacto";

		// ARRANGE
		ServicioDTO service = new ServicioDTO(id, pNombre, pDesc, pDesc, pDesc, pTexto, false, pTextoOnline, false,
				pTextoTlf, pDest, pInicio, pCoste, pPlazo, pOrgano, pRecursos, pFundamento, pPreguntas, pContacto);
		service.setDenominacion(pDen);
		service.setDescripcion(pDesc);
		service.setDocumentacion(pDoc);
		service.setTipoTramite(pTipo);
		service.setIdSubCategoria(id);
		// ASSERT

		assertEquals(pDen, service.getDenominacion());
		assertEquals(pDesc, service.getDescripcion());
		assertEquals(pDoc, service.getDocumentacion());
		assertEquals(pTipo, service.getTipoTramite());
		assertEquals(pTexto, service.getTextoPresencial());
		assertEquals(pTextoOnline, service.getTextoOnline());
		assertEquals(id, service.getIdSubCategoria());
		assertFalse(service.getCanalOnline());
		assertFalse(service.getCanalTelefonico());
		assertEquals(pTextoTlf, service.getTextoTelefonico());
		assertEquals(pDest, service.getDestinatarios());
		assertEquals(pInicio, service.getFormaInicio());
		assertEquals(pCoste, service.getCosteYFormadePago());
		assertEquals(pPlazo, service.getPlazoResolucion());
		assertEquals(pOrgano, service.getOrganosCompetentes());
		assertEquals(pRecursos, service.getRecursos());
		assertEquals(pFundamento, service.getFundamentoLegal());
		assertEquals(pPreguntas, service.getPreguntasFrecuentes());
		assertEquals(pContacto, service.getContactar());
	}

}
