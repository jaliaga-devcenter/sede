package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class AdjudicacionTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAdjudicacion() {
		String pDesc = "desc";
		String pEmp = "adju";
		String pPlica = "plica";
		String pLang = "es";
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		BigDecimal bd = new BigDecimal(1024.50);

		// ARRANGE
		Adjudicacion adju = new Adjudicacion();

		adju.setPresupuesto(bd);
		adju.setResultado(file);
		adju.setFecha(date);
		adju.setFechaAdjudicacion(date);
		adju.setFechaFormalizacion(date);
		adju.setHora(date);

		adju.setFileToUpload(null);
		adju.setTraducciones(new ArrayList<>());
		adju.getTraducciones().add(new AdjudicacionLenguaje(pLang));
		adju.getTraducciones().get(0).setDenominacion(pDesc);
		adju.getTraducciones().get(0).setPlica(pPlica);
		adju.getTraducciones().get(0).setEmpresaAdjudicacion(pEmp);
		// ASSERT

		assertEquals(bd, adju.getPresupuesto());
		assertEquals(file, adju.getResultado());
		assertEquals(date, adju.getFecha());
		assertEquals(date, adju.getFechaAdjudicacion());
		assertEquals(date, adju.getFechaFormalizacion());
		assertEquals(date, adju.getHora());
		assertNull(adju.getFileToUpload());
		assertNotNull(adju.getResultado());
		assertEquals(adju.getTraducciones().get(0).getDenominacion(), pDesc);
		assertEquals(adju.getTraducciones().get(0).getPlica(), pPlica);
		assertEquals(adju.getTraducciones().get(0).getEmpresaAdjudicacion(), pEmp);
		assertEquals(adju.getTraducciones().get(0).getIdioma(), pLang);
	}
}
