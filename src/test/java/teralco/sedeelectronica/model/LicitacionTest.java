package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

public class LicitacionTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testLicitacion() {
		// ARRANGE
		Licitacion lici = new Licitacion();

		lici.setDescripcion("cualquier descripción");
		lici.setMedio(Medio.BOE);
		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		lici.setFichero(file);
		BigDecimal bd = new BigDecimal(1024.50);
		lici.setPresupuesto(bd);

		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);

		lici.setFechaPub(date);
		lici.setFinPlazo(date);

		// ASSERT
		assertEquals("cualquier descripción", lici.getDescripcion());
		assertEquals(file, lici.getFichero());
		assertEquals(bd, lici.getPresupuesto());
		assertNotEquals(Medio.BORM, lici.getMedio());
		assertEquals(date, lici.getFechaPub());
		assertEquals(date, lici.getFinPlazo());
	}
}
