package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class AperturaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAdjudicacion() {
		// DECLARE VARIABLES
		String plica = "esto es una plica";
		String desc = "cualquier descripción";
		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		// ARRANGE
		Apertura aper = new Apertura();
		aper.setDenominacion(desc);
		aper.setPlica(plica);
		aper.setResultado(file);
		aper.setFecha(date);
		aper.setHora(date);

		// ASSERT
		assertEquals(desc, aper.getDenominacion());
		assertEquals(plica, aper.getPlica());
		assertEquals(file, aper.getResultado());
		assertEquals(date, aper.getFecha());
		assertEquals(date, aper.getHora());

	}
}
