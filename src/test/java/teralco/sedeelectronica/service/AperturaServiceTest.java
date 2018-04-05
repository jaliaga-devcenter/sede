package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.repository.AperturaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
public class AperturaServiceTest {

	@Autowired
	private AperturaService aperturaService;

	@MockBean
	private AperturaRepository aperturaRepository;

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
		Apertura aper = new Apertura();
		aper.setId(1L);
		aper.setFecha(date);
		/* ADJU 2 */
		Apertura aper2 = new Apertura();
		aper2.setId(2L);
		aper2.setFecha(date);
		/* ADJU 3 */
		Apertura aper3 = new Apertura();
		aper3.setId(3L);
		aper3.setFecha(date);
		/* ADJU 4 */
		Apertura aper4 = new Apertura();
		aper4.setId(4L);
		aper4.setFecha(date);
		Mockito.when(this.aperturaRepository.findOne(aper.getId())).thenReturn(aper);
		Mockito.when(this.aperturaRepository.findOne(aper2.getId())).thenReturn(aper2);
		Mockito.when(this.aperturaRepository.findOne(aper3.getId())).thenReturn(aper3);
		Mockito.when(this.aperturaRepository.findOne(aper4.getId())).thenReturn(aper4);

	}

	@Test
	public void whenFindAper() {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		date.setSeconds(0);

		Apertura found_1 = this.aperturaService.get(4L);
		Apertura found_2 = this.aperturaService.get(3L);
		Apertura found_3 = this.aperturaService.get(2L);
		Apertura found_4 = this.aperturaService.get(1L);

		assertThat(found_4.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_3.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_2.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_1.getFecha().getDate()).isEqualTo(date.getDate());
	}
}
