package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CSVValidationTest {

	@Test
	public void testAdjudicacion() {
		// ARRANGE
		String idcsv = "1567896143547f65a4sdf65";
		CSVValidation csv = new CSVValidation();

		csv.setCsv(idcsv);

		// ASSERT
		assertEquals(idcsv, csv.getCsv());
	}
}
