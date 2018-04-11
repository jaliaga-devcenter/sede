package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.model.Fichero;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)

public class AvisoRepositoryTest {
	@Autowired
	private AvisoRepository avisoRepository;

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
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		Aviso aviso = new Aviso();
		aviso.setFichero(file);
		aviso.setFecha(date);

		aviso = this.avisoRepository.save(aviso);

		// ACT
		Aviso found = this.avisoRepository.findById(aviso.getId());

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(aviso.getFichero().getTamanyo());
	}

	@Test
	@Transactional
	@DirtiesContext
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		file.setTamanyo(4.0);
		this.ficheroRepository.save(file);
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		Aviso aviso = new Aviso();
		aviso.setFichero(file);
		aviso.setFecha(date);

		aviso = this.avisoRepository.save(aviso);

		// ACT
		Aviso found = this.avisoRepository.findById(aviso.getId());
		found.getFichero().setTamanyo(64.0);
		aviso = this.avisoRepository.save(found);

		// ASSERT
		assertThat(found.getFichero().getTamanyo()).isEqualTo(aviso.getFichero().getTamanyo());
	}

	@Test
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		Aviso aviso = new Aviso();
		aviso.setFichero(file);
		aviso.setFecha(date);
		aviso = this.avisoRepository.save(aviso);

		// ACT
		Aviso found = this.avisoRepository.findById(aviso.getId());
		this.avisoRepository.delete(found);

		found = this.avisoRepository.findById(aviso.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.avisoRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
