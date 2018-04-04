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
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
@Configuration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AperturaRepositoryTest {

	@Autowired
	private AperturaRepository aperturaRepository;

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
		Apertura apertura = new Apertura();
		apertura.setResultado(file);
		apertura.setFecha(date);
		apertura.setHora(date);

		apertura = this.aperturaRepository.saveAndFlush(apertura);
		// ACT
		Apertura found = this.aperturaRepository.findById(apertura.getId());

		// ASSERT
		assertThat(found.getResultado().getTamanyo()).isEqualTo(apertura.getResultado().getTamanyo());
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
		Apertura apertura = new Apertura();
		apertura.setResultado(file);
		apertura.setFecha(date);
		apertura.setHora(date);
		apertura = this.aperturaRepository.save(apertura);

		// ACT
		Apertura found = this.aperturaRepository.findById(apertura.getId());
		found.getResultado().setTamanyo(64.0);

		this.aperturaRepository.save(found);
		apertura = this.aperturaRepository.findById(apertura.getId());

		// ASSERT
		assertThat(found.getResultado().getTamanyo()).isEqualTo(64.0);
	}

	@Test
	@Transactional
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
		Apertura apertura = new Apertura();
		apertura.setResultado(file);
		apertura.setFecha(date);
		apertura.setHora(date);
		apertura = this.aperturaRepository.save(apertura);

		// ACT
		Apertura found = this.aperturaRepository.findById(apertura.getId());
		this.aperturaRepository.delete(found);
		found = this.aperturaRepository.findById(apertura.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.aperturaRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
