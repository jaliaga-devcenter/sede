package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

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
import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.model.Fichero;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@Configuration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AdjudicacionRepositoryTest {

	@Autowired
	private AdjudicacionRepository adjudicacionRepository;

	@Autowired
	private FicheroRepository ficheroRepository;

	@Test
	@Transactional
	@DirtiesContext
	public void saveTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Adjudicacion adju = new Adjudicacion();
		adju.setFecha(null);
		adju.setFechaAdjudicacion(null);
		adju.setHora(null);
		adju.setPresupuesto(null);
		adju.setResultado(file);

		adju = this.adjudicacionRepository.saveAndFlush(adju);

		// ACT
		Adjudicacion found = this.adjudicacionRepository.findById(adju.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(adju.getId());
	}

	@Test
	@Transactional
	@DirtiesContext
	public void editTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Adjudicacion adju = new Adjudicacion();
		adju.setResultado(file);
		adju.setPresupuesto(new BigDecimal(1024.5));
		adju = this.adjudicacionRepository.saveAndFlush(adju);

		// ACT
		Adjudicacion found = this.adjudicacionRepository.findById(adju.getId());
		found.setPresupuesto(new BigDecimal(512));
		this.adjudicacionRepository.save(found);

		adju = this.adjudicacionRepository.findById(adju.getId());

		// ASSERT
		assertThat(found.getPresupuesto()).isEqualTo(adju.getPresupuesto());
	}

	@Test
	@Transactional
	public void removeTest() {
		// ARRANGE
		Fichero file = new Fichero();
		this.ficheroRepository.save(file);

		Adjudicacion adju = new Adjudicacion();
		adju.setResultado(file);
		adju = this.adjudicacionRepository.save(adju);

		// ACT
		Adjudicacion found = this.adjudicacionRepository.findById(adju.getId());
		this.adjudicacionRepository.delete(found);

		found = this.adjudicacionRepository.findById(adju.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.adjudicacionRepository.deleteAll();
		this.ficheroRepository.deleteAll();
	}

}
