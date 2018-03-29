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

import teralco.sedeelectronica.app.Application;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@Ignore
public class FicheroRepositoryTest {

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setNombreOriginal("unnombre.pdf");
		file = this.ficheroRepository.save(file);

		// ACT
		Fichero found = this.ficheroRepository.findById(file.getId());

		// ASSERT
		assertThat(found.getNombreOriginal()).isEqualTo(file.getNombreOriginal());
	}

	@Test
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setNombreOriginal("unnombre.pdf");
		file = this.ficheroRepository.save(file);

		// ACT
		Fichero found = this.ficheroRepository.findById(file.getId());
		found.setNombreOriginal("otronombre.doc");

		this.ficheroRepository.save(found);

		file = this.ficheroRepository.findById(file.getId());

		// ASSERT
		assertThat(found.getNombreOriginal()).isEqualTo(file.getNombreOriginal());
	}

	@Test
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setNombreOriginal("unnombre.pdf");
		file = this.ficheroRepository.save(file);

		// ACT
		Fichero found = this.ficheroRepository.findById(file.getId());
		this.ficheroRepository.delete(found);

		found = this.ficheroRepository.findById(file.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.ficheroRepository.deleteAll();
	}

}
