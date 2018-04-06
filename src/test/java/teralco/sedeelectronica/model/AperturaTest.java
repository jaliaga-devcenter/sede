package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class AperturaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAdjudicacion() {
		String pDesc = "desc";
		String pPlica = "plica";
		String pLang = "es";
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
		aper.setResultado(file);
		aper.setFecha(date);
		aper.setHora(date);

		aper.setFileToUpload(null);

		aper.setTraducciones(new ArrayList<>());
		aper.getTraducciones().add(new AperturaLenguaje(pLang));
		aper.getTraducciones().get(0).setDenominacion(pDesc);
		aper.getTraducciones().get(0).setPlica(pPlica);

		// ASSERT
		assertEquals(file, aper.getResultado());
		assertEquals(date, aper.getFecha());
		assertEquals(date, aper.getHora());
		assertNull(aper.getFileToUpload());
		assertEquals(aper.getTraducciones().get(0).getDenominacion(), pDesc);
		assertEquals(aper.getTraducciones().get(0).getPlica(), pPlica);
		assertEquals(aper.getTraducciones().get(0).getIdioma(), pLang);
	}
}
