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
		String pArticulo = "nº 145";
		String pNorma = "una norma";
		String pTexto = "el texto";
		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setArticulo(pArticulo);
		norma.setNorma(pNorma);
		norma.setTexto(pTexto);
		norma.setUrl(pUrl);

		norma = this.normativaRepository.save(norma);

		// ACT
		Normativa found = this.normativaRepository.findById(norma.getId());

		// ASSERT
		assertThat(found.getArticulo()).isEqualTo(norma.getArticulo());
	}

	@Test
	public void editTest() {
		// DECLARE VARIABLES
		String pArticulo = "nº 145";
		String pNorma = "una norma";
		String pTexto = "el texto";
		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setArticulo(pArticulo);
		norma.setNorma(pNorma);
		norma.setTexto(pTexto);
		norma.setUrl(pUrl);

		norma = this.normativaRepository.save(norma);
		// ACT
		Normativa found = this.normativaRepository.findById(norma.getId());

		found.setArticulo("esto es otro articulo");

		this.normativaRepository.save(found);

		norma = this.normativaRepository.findById(norma.getId());

		// ASSERT
		assertThat(found.getArticulo()).isEqualTo(norma.getArticulo());
	}

	@Test
	public void removeTest() {
		// DECLARE VARIABLES
		String pArticulo = "nº 145";
		String pNorma = "una norma";
		String pTexto = "el texto";
		String pUrl = "www.google.es";

		// ARRANGE
		Normativa norma = new Normativa();
		norma.setArticulo(pArticulo);
		norma.setNorma(pNorma);
		norma.setTexto(pTexto);
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
