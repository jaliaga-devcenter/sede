package teralco.sedeelectronica.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

public class ModeloTest {
	@Test
	public void testModelo() {
		String pDesc = "una descripcion";
		String pIdio = "es";
		// ARRANGE
		Modelo model = new Modelo();

		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		model.setFichero(file);
		model.setFileToUpload(null);
		ModeloLenguaje ml = new ModeloLenguaje(pIdio);
		model.setTraducciones(new ArrayList<>());
		model.getTraducciones().add(ml);
		model.getTraducciones().get(0).setDescripcion(pDesc);

		// ASSERT
		assertEquals(file, model.getFichero());
		assertNull(model.getFileToUpload());
		assertEquals(model.getTraducciones().get(0).getDescripcion(), pDesc);

	}
}
