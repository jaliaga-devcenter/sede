package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.Application;
import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@SuppressWarnings("deprecation")
@Ignore
public class AperturaRepositoryTest {
	@Autowired
	private AperturaRepository aperturaRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	public void saveTest() {
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
		apertura.setDenominacion("una denominacion");
		apertura.setResultado(file);
		apertura.setFecha(date);
		apertura.setHora(date);
		apertura = this.aperturaRepository.save(apertura);

		// ACT
		Apertura found = this.aperturaRepository.findById(apertura.getId());

		// ASSERT
		assertThat(found.getDenominacion()).isEqualTo(apertura.getDenominacion());
	}

	@Test
	public void editTest() {
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
		apertura.setDenominacion("una denominacion");
		apertura.setResultado(file);
		apertura.setFecha(date);
		apertura.setHora(date);
		apertura = this.aperturaRepository.save(apertura);

		// ACT
		Apertura found = this.aperturaRepository.findById(apertura.getId());
		found.setDenominacion("esto es otra denominacion");

		this.aperturaRepository.save(found);

		apertura = this.aperturaRepository.findById(apertura.getId());

		// ASSERT
		assertThat(found.getDenominacion()).isEqualTo(apertura.getDenominacion());
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
		Apertura apertura = new Apertura();
		apertura.setDenominacion("una denominacion");
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
