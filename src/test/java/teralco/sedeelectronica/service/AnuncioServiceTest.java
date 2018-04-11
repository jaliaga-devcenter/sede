package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Date;
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
import teralco.sedeelectronica.model.Anuncio;
import teralco.sedeelectronica.repository.AnuncioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
public class AnuncioServiceTest {

	@Autowired
	private AnuncioService anuncioService;

	@MockBean
	private AnuncioRepository anuncioRepository;

	@Before
	public void setUp() {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		date.setSeconds(0);
		/* ADJU 1 */
		Anuncio anun = new Anuncio();
		anun.setId(1L);
		anun.setFechaDe(date);
		/* ADJU 2 */
		Anuncio anun2 = new Anuncio();
		anun2.setId(2L);
		anun2.setFechaDe(date);
		/* ADJU 3 */
		Anuncio anun3 = new Anuncio();
		anun3.setId(3L);
		anun3.setFechaDe(date);
		/* ADJU 4 */
		Anuncio anun4 = new Anuncio();
		anun4.setId(4L);
		anun4.setFechaDe(date);
		date.setMonth(5);
		Anuncio anun5 = new Anuncio();
		anun5.setId(5L);
		anun5.setFechaDe(date);
		date.setMonth(8);
		Anuncio anun6 = new Anuncio();
		anun6.setId(6L);
		anun6.setFechaDe(date);

		List<Anuncio> list = new ArrayList<>();
		list.add(anun6);
		list.add(anun5);
		list.add(anun);
		Mockito.when(this.anuncioRepository.findOne(anun.getId())).thenReturn(anun);
		Mockito.when(this.anuncioRepository.findOne(anun2.getId())).thenReturn(anun2);
		Mockito.when(this.anuncioRepository.findOne(anun3.getId())).thenReturn(anun3);
		Mockito.when(this.anuncioRepository.findOne(anun4.getId())).thenReturn(anun4);
		Mockito.when(this.anuncioRepository.findOne(anun5.getId())).thenReturn(anun5);
		Mockito.when(this.anuncioRepository.findOne(anun6.getId())).thenReturn(anun6);

		Mockito.when(this.anuncioRepository.findAllByOrderByFechaDeDesc()).thenReturn(list);

		Mockito.when(this.anuncioRepository.findAll()).thenReturn(list);
	}

	@Test
	public void whenFindAnuncio() {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		date.setSeconds(0);
		Anuncio found_1 = this.anuncioService.get(4L);
		Anuncio found_2 = this.anuncioService.get(3L);
		Anuncio found_3 = this.anuncioService.get(2L);
		Anuncio found_4 = this.anuncioService.get(1L);

		assertThat(found_4.getFechaDe().getDate()).isEqualTo(date.getDate());
		assertThat(found_3.getFechaDe().getDate()).isEqualTo(date.getDate());
		assertThat(found_2.getFechaDe().getDate()).isEqualTo(date.getDate());
		assertThat(found_1.getFechaDe().getDate()).isEqualTo(date.getDate());
	}

	@Test
	public void whenListAnuncio() {
		List<Anuncio> listFound = this.anuncioService.listAll();
		Anuncio found_2 = this.anuncioService.get(1L);
		Anuncio found_3 = this.anuncioService.get(5L);
		Anuncio found_4 = this.anuncioService.get(6L);

		assertNotSame(listFound.get(0), found_2);
		assertNotSame(listFound.get(1), found_4);
		assertNotSame(listFound.get(2), found_3);

		listFound = (List<Anuncio>) this.anuncioService.list();

		assertThat(listFound.size()).isEqualTo(3);

	}

}
