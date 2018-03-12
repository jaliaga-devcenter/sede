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
public class LicitacionRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private LicitacionRepository licitacionRepository;

	@Test
	public void saveTest() {
		assertNotNull(this.entityManager);
		assertNotNull(this.licitacionRepository);
	}

}
