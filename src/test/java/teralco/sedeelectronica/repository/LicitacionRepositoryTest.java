package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Licitacion;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
public class LicitacionRepositoryTest {

	@Autowired
	private LicitacionRepository licitacionRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Licitacion lici = new Licitacion();
		BigDecimal bd = new BigDecimal(1024.5);
		lici.setPresupuesto(bd);

		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		lici.setMedio((short) 1);
		lici.setFechaPub(date);
		lici.setFinPlazo(date);
		lici.setFichero(file);

		lici = this.licitacionRepository.save(lici);

		// ACT
		Licitacion found = this.licitacionRepository.findById(lici.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(lici.getId());
	}

	@Test
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Licitacion lici = new Licitacion();
		lici.setFichero(file);
		BigDecimal bd = new BigDecimal(1024.50);
		lici.setPresupuesto(bd);

		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		lici.setMedio((short) 1);

		lici.setFechaPub(date);
		lici.setFinPlazo(date);
		lici = this.licitacionRepository.save(lici);

		// ACT
		Licitacion found = this.licitacionRepository.findById(lici.getId());
		bd = new BigDecimal(812.50);
		lici.setPresupuesto(bd);
		this.licitacionRepository.save(found);

		lici = this.licitacionRepository.findById(lici.getId());

		// ASSERT
		assertThat(found.getPresupuesto()).isEqualTo(lici.getPresupuesto());
	}

	@Test
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Licitacion lici = new Licitacion();
		lici.setFichero(file);
		BigDecimal bd = new BigDecimal(1024.50);
		lici.setPresupuesto(bd);

		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		lici.setMedio((short) 1);
		lici.setFechaPub(date);
		lici.setFinPlazo(date);
		lici = this.licitacionRepository.save(lici);

		// ACT
		Licitacion found = this.licitacionRepository.findById(lici.getId());
		this.licitacionRepository.delete(found);
		found = this.licitacionRepository.findById(lici.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.licitacionRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
