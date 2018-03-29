package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Noticia;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
@Ignore
public class NoticiaRepositoryTest {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Test
	public void saveTest() {
		// DECLARE VARIABLES
		// ARRANGE
		Noticia news = new Noticia();

		news = this.noticiaRepository.save(news);

		// ACT
		Noticia found = this.noticiaRepository.findById(news.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(news.getId());
	}

	@Test
	public void editTest() {
		// DECLARE VARIABLES
		// ARRANGE
		Noticia news = new Noticia();

		news = this.noticiaRepository.save(news);

		// ACT
		Noticia found = this.noticiaRepository.findById(news.getId());

		this.noticiaRepository.save(found);

		news = this.noticiaRepository.findById(news.getId());

		// ASSERT
		assertThat(found.getId()).isEqualTo(news.getId());
	}

	@Test
	public void removeTest() {
		// DECLARE VARIABLES
		// ARRANGE
		Noticia news = new Noticia();

		news = this.noticiaRepository.save(news);

		// ACT
		Noticia found = this.noticiaRepository.findById(news.getId());
		this.noticiaRepository.delete(found);
		found = this.noticiaRepository.findById(news.getId());

		// ASSERT
		assertNull(found);
	}

	@After
	public void delete() {
		this.noticiaRepository.deleteAll();
	}

}
