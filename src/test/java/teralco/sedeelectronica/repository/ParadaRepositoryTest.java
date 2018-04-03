package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Parada;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
public class ParadaRepositoryTest {

	@Autowired
	private ParadaRepository paradaRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	public void saveTest() {
		// DECLARE VARIABLES
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		// ARRANGE
		Parada parada = new Parada();
		parada.setFecha(date);
		parada = this.paradaRepository.save(parada);

		// ACT
		Parada found = this.paradaRepository.findById(parada.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(parada.getId());
	}

	@Test
	public void editTest() {
		// DECLARE VARIABLES
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		// ARRANGE
		Parada parada = new Parada();
		parada.setFecha(date);
		parada = this.paradaRepository.save(parada);

		// ACT
		Parada found = this.paradaRepository.findById(parada.getId());

		this.paradaRepository.save(found);

		parada = this.paradaRepository.findById(parada.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(parada.getId());
	}

	@Test
	public void removeTest() {
		// DECLARE VARIABLES
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		// ARRANGE
		Parada parada = new Parada();
		parada.setFecha(date);
		parada = this.paradaRepository.save(parada);

		// ACT
		Parada found = this.paradaRepository.findById(parada.getId());
		this.paradaRepository.delete(found);
		found = this.paradaRepository.findById(parada.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.paradaRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
