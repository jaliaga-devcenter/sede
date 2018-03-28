package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class AnuncioTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAnuncio() {
		String desc = "cualquier descripción";
		String titulo = "un titulo";
		// ARRANGE
		Anuncio anuncio = new Anuncio();
		anuncio.setDescripcion(desc);

		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		anuncio.setFechaDe(date);
		anuncio.setFechaHasta(date);
		anuncio.setTitulo(titulo);
		// ASSERT
		assertEquals(desc, anuncio.getDescripcion());
		assertEquals(titulo, anuncio.getTitulo());
		assertEquals(date, anuncio.getFechaDe());
		assertEquals(date, anuncio.getFechaHasta());

	}
}
