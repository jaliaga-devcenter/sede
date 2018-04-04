package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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
@SuppressWarnings("deprecation")
public class AnuncioRepositoryTest {

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	@Transactional
	@DirtiesContext
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setTamanyo(4.0);
		this.ficheroRepository.save(file);
		Anuncio anuncio = new Anuncio();
		anuncio.setFichero(file);
		anuncio = this.anuncioRepository.save(anuncio);

		// ACT
		Anuncio found = this.anuncioRepository.findById(anuncio.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(anuncio.getFichero().getTamanyo());
	}

	@Test
	@DirtiesContext
	@Transactional
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setTamanyo(4.0);
		this.ficheroRepository.save(file);
		Anuncio anuncio = new Anuncio();
		anuncio.setFichero(file);
		anuncio = this.anuncioRepository.save(anuncio);

		// ACT
		Anuncio found = this.anuncioRepository.findById(anuncio.getId());
		found.getFichero().setTamanyo(64.0);
		this.anuncioRepository.save(found);

		anuncio = this.anuncioRepository.findById(anuncio.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(anuncio.getFichero().getTamanyo());
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

	@Test
	@Transactional
	@DirtiesContext
	public void listTest() {
		// DECLARE VARIABLES
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Anuncio anuncio = new Anuncio();
		anuncio.setFichero(file);
		anuncio.setFechaDe(new Date(2018, 4, 3));
		this.anuncioRepository.save(anuncio);
		Anuncio anuncio2 = new Anuncio();
		anuncio2.setFichero(file);
		anuncio2.setFechaDe(new Date(2018, 4, 9));
		this.anuncioRepository.save(anuncio2);

		// ACT
		List<Anuncio> n1 = this.anuncioRepository.findAll();
		List<Anuncio> n2 = this.anuncioRepository.findAllByOrderByFechaDeDesc();

		// ASSERT
		assertNotSame(n1.get(0), n2.get(0));
		assertNotSame(n1.get(1), n2.get(1));
	}

	@After
	public void delete() {
		this.anuncioRepository.deleteAll();
	}

}
