package teralco.sedeelectronica.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import teralco.sedeelectronica.model.Noticia;
import teralco.sedeelectronica.model.NoticiaLenguaje;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
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
	@Transactional
	@DirtiesContext
	public void editTest() {
		// DECLARE VARIABLES
		String titulo = "es un titulo";
		String noticia = "noticiaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String noticia2 = "esto es una edicion";
		// ARRANGE
		Noticia news = new Noticia();
		news.getTraducciones().add(new NoticiaLenguaje("es"));
		news.getTraducciones().get(0).setTitulo(titulo);
		news.getTraducciones().get(0).setDescripcion(noticia);
		news = this.noticiaRepository.save(news);

		// ACT
		Noticia found = this.noticiaRepository.findById(news.getId());
		found.getTraducciones().get(0).setDescripcion(noticia2);
		this.noticiaRepository.save(found);

		news = this.noticiaRepository.findById(news.getId());

		// ASSERT
		assertThat(found.getTraducciones().get(0).getDescripcion())
				.isEqualTo(news.getTraducciones().get(0).getDescripcion());
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

	@Test
	@Transactional
	@DirtiesContext
	public void listTest() {
		// DECLARE VARIABLES
		// ARRANGE
		Noticia news = new Noticia();
		news.setFecha(Timestamp.valueOf(LocalDateTime.now().minusDays(10)));
		news = this.noticiaRepository.save(news);
		Noticia news2 = new Noticia();
		news2.setFecha(Timestamp.valueOf(LocalDateTime.now()));
		news2 = this.noticiaRepository.save(news2);

		// ACT
		List<Noticia> n1 = this.noticiaRepository.findAll();
		List<Noticia> n2 = this.noticiaRepository.findAllByOrderByFechaDesc();

		// ASSERT
		assertNotSame(n1.get(0), n2.get(0));
		assertNotSame(n1.get(1), n2.get(1));
	}

	@After
	public void delete() {
		this.noticiaRepository.deleteAll();
	}

}
