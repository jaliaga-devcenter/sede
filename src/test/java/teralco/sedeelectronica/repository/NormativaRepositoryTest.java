package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Normativa;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class NormativaRepositoryTest {

	@Autowired
	private NormativaRepository normativaRepository;

	@Test
	public void saveTest() {
		// DECLARE VARIABLES
		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setUrl(pUrl);

		norma = this.normativaRepository.save(norma);

		// ACT
		Normativa found = this.normativaRepository.findById(norma.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(norma.getId());
	}

	@Test
	public void editTest() {
		// DECLARE VARIABLES

		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setUrl(pUrl);

		norma = this.normativaRepository.save(norma);
		// ACT
		Normativa found = this.normativaRepository.findById(norma.getId());

		this.normativaRepository.save(found);

		norma = this.normativaRepository.findById(norma.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(norma.getId());
	}

	@Test
	public void removeTest() {
		// DECLARE VARIABLES

		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setUrl(pUrl);

		norma = this.normativaRepository.save(norma);
		// ACT
		Normativa found = this.normativaRepository.findById(norma.getId());
		this.normativaRepository.delete(found);
		found = this.normativaRepository.findById(norma.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.normativaRepository.deleteAll();
	}

}
