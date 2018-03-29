package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class AvisoTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAdjudicacion() {
		// DECLARE VARIABLES
		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);

		// ARRANGE
		Aviso aviso = new Aviso();
		aviso.setFecha(date);
		aviso.setFichero(file);

		// ASSERT
		assertEquals(file, aviso.getFichero());
		assertEquals(date, aviso.getFecha());

	}
}
