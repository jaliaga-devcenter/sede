package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.Application;
import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class DocumentacionRepositoryTest {
	@Autowired
	private DocumentacionRepository documentacionRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Documentacion docu = new Documentacion();
		docu.setDescripcion("una denominacion");
		docu.setFichero(file);
		docu = this.documentacionRepository.save(docu);

		// ACT
		Documentacion found = this.documentacionRepository.findById(docu.getId());

		// ASSERT
		assertThat(found.getDescripcion()).isEqualTo(docu.getDescripcion());
	}

	@Test
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Documentacion docu = new Documentacion();
		docu.setDescripcion("una denominacion");
		docu.setFichero(file);
		docu = this.documentacionRepository.save(docu);

		// ACT
		Documentacion found = this.documentacionRepository.findById(docu.getId());
		found.setDescripcion("esto es otra denominacion");

		this.documentacionRepository.save(found);

		docu = this.documentacionRepository.findById(docu.getId());

		// ASSERT
		assertThat(found.getDescripcion()).isEqualTo(docu.getDescripcion());
	}

	@Test
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Documentacion docu = new Documentacion();
		docu.setDescripcion("una denominacion");
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
