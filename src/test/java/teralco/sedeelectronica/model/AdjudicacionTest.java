package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

public class AdjudicacionTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAdjudicacion() {
		// DECLARE VARIABLES
		String empresa = "timofonica";
		String desc = "cualquier descripción";
		String plica = "esto es una plica";
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

		adju.setEmpresaAdjudicacion(empresa);
		adju.setDenominacion(desc);
		adju.setPlica(plica);
		adju.setPresupuesto(bd);
		adju.setResultado(file);
		adju.setFecha(date);
		adju.setFechaAdjudicacion(date);
		adju.setFechaFormalizacion(date);
		adju.setHora(date);

		// ASSERT
		assertEquals(empresa, adju.getEmpresaAdjudicacion());
		assertEquals(desc, adju.getDenominacion());
		assertEquals(plica, adju.getPlica());
		assertEquals(bd, adju.getPresupuesto());
		assertEquals(file, adju.getResultado());
		assertEquals(date, adju.getFecha());
		assertEquals(date, adju.getFechaAdjudicacion());
		assertEquals(date, adju.getFechaFormalizacion());
		assertEquals(date, adju.getHora());

	}
}
