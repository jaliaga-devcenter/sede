package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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
		assertThat(found.getFecha().getDay()).isEqualTo(parada.getFecha().getDay());
		assertThat(found.getFecha().getMonth()).isEqualTo(parada.getFecha().getMonth());
		assertThat(found.getFecha().getYear()).isEqualTo(parada.getFecha().getYear());

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
		date.setYear(2018);
		date.setMonth(5);
		date.setDate(3);
		found.setFecha(date);
		this.paradaRepository.save(found);

		parada = this.paradaRepository.findById(parada.getId());

		// ASSERT
		assertThat(found.getFecha().getDay()).isEqualTo(parada.getFecha().getDay());
		assertThat(found.getFecha().getMonth()).isEqualTo(parada.getFecha().getMonth());
		assertThat(found.getFecha().getYear()).isEqualTo(parada.getFecha().getYear());
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

	@Test
	@Transactional
	@DirtiesContext
	public void listTest() {
		// ARRANGE

		Parada parada1 = new Parada();
		parada1.setFecha(new Date(2020, 4, 3));
		this.paradaRepository.save(parada1);
		Parada parada2 = new Parada();
		parada2.setFecha(new Date(2020, 4, 9));
		this.paradaRepository.save(parada2);
		Parada parada3 = new Parada();
		parada3.setFecha(new Date(2016, 4, 9));
		this.paradaRepository.save(parada3);

		// ACT
		List<Parada> n1 = this.paradaRepository.findAll();
		List<Parada> n2 = this.paradaRepository.findAllByFechaGreaterThanEqualOrderByFecha(new Date(2018, 4, 4));

		// ASSERT
		assertSame(n1.get(0), n2.get(0));
		assertSame(n1.get(1), n2.get(1));
	}

	@After
	public void delete() {
		this.paradaRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
