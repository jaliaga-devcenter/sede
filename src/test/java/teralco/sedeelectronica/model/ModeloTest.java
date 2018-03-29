package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ModeloTest {
	@Test
	public void testModelo() {
		// ARRANGE
		Modelo model = new Modelo();

		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		model.setFichero(file);

		// ASSERT
		assertEquals(file, model.getFichero());

	}
}
