package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class LicitacionTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testLicitacion() {
		String pDesc = "desc";
		String pLang = "es";
		Short medio = 1;
		// ARRANGE
		Licitacion lici = new Licitacion();

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
		lici.setFileToUpload(null);
		lici.setMedio(medio);
		lici.setTraducciones(new ArrayList<>());
		lici.getTraducciones().add(new LicitacionLenguaje(pLang));
		lici.getTraducciones().get(0).setDescripcion(pDesc);

		// ASSERT
		assertEquals(file, lici.getFichero());
		assertEquals(bd, lici.getPresupuesto());
		assertEquals(date, lici.getFechaPub());
		assertEquals(date, lici.getFinPlazo());
		assertEquals(medio, lici.getMedio());
		assertNull(lici.getFileToUpload());
		assertEquals(lici.getTraducciones().get(0).getIdioma(), pLang);
		assertEquals(lici.getTraducciones().get(0).getDescripcion(), pDesc);
	}
}
