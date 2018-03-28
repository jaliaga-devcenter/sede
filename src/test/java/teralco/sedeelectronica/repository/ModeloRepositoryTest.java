package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Modelo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@Ignore
public class ModeloRepositoryTest {

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Modelo model = new Modelo();
		model.setDescripcion("descripcion");
		model.setFichero(file);

		model = this.modeloRepository.save(model);

		// ACT
		Modelo found = this.modeloRepository.findById(model.getId());

		// ASSERT
		assertThat(found.getDescripcion()).isEqualTo(model.getDescripcion());
	}

	@Test
	public void editTest() {
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Modelo model = new Modelo();
		model.setDescripcion("descripcion");
		model.setFichero(file);

		model = this.modeloRepository.save(model);

		// ACT
		Modelo found = this.modeloRepository.findById(model.getId());

		found.setDescripcion("esto es otra denominacion");

		this.modeloRepository.save(found);

		model = this.modeloRepository.findById(model.getId());

		// ASSERT
		assertThat(found.getDescripcion()).isEqualTo(model.getDescripcion());
	}

	@Test
	public void removeTest() {
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Modelo model = new Modelo();
		model.setDescripcion("descripcion");
		model.setFichero(file);

		model = this.modeloRepository.save(model);

		// ACT
		Modelo found = this.modeloRepository.findById(model.getId());
		this.modeloRepository.delete(found);
		found = this.modeloRepository.findById(model.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.modeloRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
