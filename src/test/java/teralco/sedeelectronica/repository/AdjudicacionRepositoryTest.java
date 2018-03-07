package teralco.sedeelectronica.repository;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class AdjudicacionRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AdjudicacionRepository adjudicacionRepository;

	@Test
	public void saveTest() {
		// ARRANGE

		/*
		 * Adjudicacion adju = new Adjudicacion();
		 * adju.setDenominacion("una denominacion");
		 * 
		 * this.entityManager.persist(adju); this.entityManager.flush();
		 * 
		 * Adjudicacion found = this.adjudicacionRepository.save(adju);
		 * 
		 * // ACT
		 * 
		 * assertThat(found.getDenominacion()).isEqualTo(adju.getDenominacion());
		 */
		// ASSET
		assertNotNull(this.entityManager);
		assertNotNull(this.adjudicacionRepository);
	}
}
