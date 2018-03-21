package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Adjudicacion;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@DataJpaTest
public class AdjudicacionRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AdjudicacionRepository adjudicacionRepository;

	@Test
	@Ignore
	public void saveTest() {
		// ARRANGE
		Adjudicacion adju = new Adjudicacion();
		adju.setDenominacion("una denominacion");
		this.entityManager.persist(adju);

		// ACT
		Adjudicacion found = this.adjudicacionRepository.findById((long) 1);

		// ASSERT
		assertThat(found.getDenominacion()).isEqualTo(adju.getDenominacion());
	}

}
