package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Anuncio;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })

public class AnuncioRepositoryTest {

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	@DirtiesContext
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);
		Anuncio anuncio = new Anuncio();
		anuncio.setFichero(file);
		anuncio = this.anuncioRepository.save(anuncio);

		// ACT
		Anuncio found = this.anuncioRepository.findById(anuncio.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(anuncio.getId());
	}

	@Test
	@DirtiesContext
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);
		Anuncio anuncio = new Anuncio();
		anuncio.setFichero(file);
		anuncio = this.anuncioRepository.save(anuncio);

		// ACT
		Anuncio found = this.anuncioRepository.findById(anuncio.getId());

		this.anuncioRepository.save(found);

		anuncio = this.anuncioRepository.findById(anuncio.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(anuncio.getId());
	}

	@Test
	@DirtiesContext
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);
		Anuncio anuncio = new Anuncio();

		anuncio.setFichero(file);
		anuncio = this.anuncioRepository.save(anuncio);

		// ACT
		Anuncio found = this.anuncioRepository.findById(anuncio.getId());
		this.anuncioRepository.delete(found);

		found = this.anuncioRepository.findById(anuncio.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.anuncioRepository.deleteAll();
	}

}
