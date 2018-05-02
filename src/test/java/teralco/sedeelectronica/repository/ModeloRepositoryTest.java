package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Modelo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@Configuration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ModeloRepositoryTest {

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	@Transactional
	@DirtiesContext
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setTamanyo(78.9);
		this.ficheroRepository.save(file);

		Modelo model = new Modelo();
		model.setFichero(file);
		model = this.modeloRepository.saveAndFlush(model);
		// ACT
		Modelo found = this.modeloRepository.findById(model.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(model.getFichero().getTamanyo());
	}

	@Test
	@Transactional
	@DirtiesContext
	public void editTest() {
		Fichero file = new Fichero();
		file.setTamanyo(1.0);
		this.ficheroRepository.save(file);

		Modelo model = new Modelo();
		model.setFichero(file);

		model = this.modeloRepository.save(model);

		// ACT
		Modelo found = this.modeloRepository.findById(model.getId());
		found.getFichero().setTamanyo(12.0);
		this.modeloRepository.save(found);

		model = this.modeloRepository.findById(model.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(model.getFichero().getTamanyo());
	}

	@Test
	@Transactional
	public void removeTest() {
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Modelo model = new Modelo();
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
