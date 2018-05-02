package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Noticia;
import teralco.sedeelectronica.repository.NoticiaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class NoticiaServiceTest {

	@Autowired
	private NoticiaService noticiaService;

	@MockBean
	private NoticiaRepository noticiaRepository;

	private Timestamp date1;
	private Timestamp date2;
	private Timestamp date3;
	private Timestamp date4;

	@Before
	public void setUp() {
		this.date1 = Timestamp.valueOf(LocalDateTime.now().minusDays(10));
		this.date2 = Timestamp.valueOf(LocalDateTime.now().minusDays(5));
		this.date3 = Timestamp.valueOf(LocalDateTime.now());
		this.date4 = Timestamp.valueOf(LocalDateTime.now().plusDays(5));
		/* ADJU 1 */
		Noticia noti = new Noticia();
		noti.setId(1L);
		noti.setFecha(this.date1);
		/* ADJU 2 */
		Noticia noti2 = new Noticia();
		noti2.setId(2L);
		noti2.setFecha(this.date2);
		/* ADJU 3 */
		Noticia noti3 = new Noticia();
		noti3.setId(3L);
		noti3.setFecha(this.date3);
		/* ADJU 4 */
		Noticia noti4 = new Noticia();
		noti4.setId(4L);
		noti4.setFecha(this.date4);

		List<Noticia> list = new ArrayList<>();
		list.add(noti4);
		list.add(noti);

		Mockito.when(this.noticiaRepository.findOne(noti.getId())).thenReturn(noti);
		Mockito.when(this.noticiaRepository.findOne(noti2.getId())).thenReturn(noti2);
		Mockito.when(this.noticiaRepository.findOne(noti3.getId())).thenReturn(noti3);
		Mockito.when(this.noticiaRepository.findOne(noti4.getId())).thenReturn(noti4);
		Mockito.when(this.noticiaRepository.findAll()).thenReturn(list);
		Mockito.when(this.noticiaRepository.findAllByOrderByFechaDesc()).thenReturn(list);

	}

	@Test
	public void whenFindNoticia() {

		Noticia found_1 = this.noticiaService.get(4L);
		Noticia found_2 = this.noticiaService.get(3L);
		Noticia found_3 = this.noticiaService.get(2L);
		Noticia found_4 = this.noticiaService.get(1L);

		assertThat(found_4.getFecha()).isEqualTo(this.date1);
		assertThat(found_3.getFecha()).isEqualTo(this.date2);
		assertThat(found_2.getFecha()).isEqualTo(this.date3);
		assertThat(found_1.getFecha()).isEqualTo(this.date4);
	}

	@Test
	public void whenListNoticia() {
		List<Noticia> listFound = this.noticiaService.listAll();
		Noticia found_2 = this.noticiaService.get(1L);
		Noticia found_3 = this.noticiaService.get(4L);

		assertNotSame(listFound.get(0), found_2);
		assertNotSame(listFound.get(1), found_3);
		listFound = (List<Noticia>) this.noticiaService.list();
		assertThat(listFound.size()).isEqualTo(2);

	}
}
