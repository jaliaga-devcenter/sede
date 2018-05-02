package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class AnuncioTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAnuncio() {
		String pDesc = "desc";
		String pTitulo = "titul";
		String pLang = "es";
		// ARRANGE
		Anuncio anuncio = new Anuncio();
		Fichero file = new Fichero();
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		anuncio.setFechaDe(date);
		anuncio.setFechaHasta(date);
		anuncio.setFileToUpload(null);
		anuncio.setFichero(file);

		anuncio.setTraducciones(new ArrayList<>());
		anuncio.getTraducciones().add(new AnuncioLenguaje(pLang));
		anuncio.getTraducciones().get(0).setDescripcion(pDesc);
		anuncio.getTraducciones().get(0).setTitulo(pTitulo);

		// ASSERT

		assertEquals(date, anuncio.getFechaDe());
		assertEquals(date, anuncio.getFechaHasta());
		assertNull(anuncio.getFileToUpload());
		assertEquals(file, anuncio.getFichero());
		assertEquals(anuncio.getTraducciones().get(0).getDescripcion(), pDesc);
		assertEquals(anuncio.getTraducciones().get(0).getTitulo(), pTitulo);
		assertEquals(anuncio.getTraducciones().get(0).getIdioma(), pLang);

	}
}
