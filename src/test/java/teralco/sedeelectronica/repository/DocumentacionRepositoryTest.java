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
import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@Configuration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DocumentacionRepositoryTest {
	@Autowired
	private DocumentacionRepository documentacionRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	@Transactional
	@DirtiesContext
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setTamanyo(69.0);
		this.ficheroRepository.save(file);

		Documentacion docu = new Documentacion();
		docu.setFichero(file);
		docu = this.documentacionRepository.saveAndFlush(docu);

		// ACT
		Documentacion found = this.documentacionRepository.findById(docu.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(docu.getFichero().getTamanyo());
	}

	@Test
	@Transactional
	@DirtiesContext
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setTamanyo(4.0);
		this.ficheroRepository.save(file);

		Documentacion docu = new Documentacion();
		docu.setFichero(file);
		docu = this.documentacionRepository.saveAndFlush(docu);

		// ACT
		Documentacion found = this.documentacionRepository.findById(docu.getId());
		found.getFichero().setTamanyo(45.0);
		this.documentacionRepository.save(found);

		docu = this.documentacionRepository.findById(docu.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(docu.getFichero().getTamanyo());
	}

	@Test
	@Transactional
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Documentacion docu = new Documentacion();
		docu.setFichero(file);
		docu = this.documentacionRepository.save(docu);

		// ACT
		Documentacion found = this.documentacionRepository.findById(docu.getId());
		this.documentacionRepository.delete(found);

		found = this.documentacionRepository.findById(docu.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.documentacionRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
