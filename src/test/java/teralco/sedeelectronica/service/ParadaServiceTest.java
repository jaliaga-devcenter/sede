package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Parada;
import teralco.sedeelectronica.repository.ParadaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
public class ParadaServiceTest {

	@Autowired
	private ParadaService paradaService;

	@MockBean
	private ParadaRepository paradaRepository;

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
		Parada parada = new Parada();
		parada.setId(1L);
		parada.setFecha(date);
		/* ADJU 2 */
		Parada parada2 = new Parada();
		parada2.setId(2L);
		parada2.setFecha(date);
		/* ADJU 3 */
		Parada parada3 = new Parada();
		parada3.setId(3L);
		parada3.setFecha(date);
		/* ADJU 4 */
		Parada parada4 = new Parada();
		parada4.setId(4L);
		parada4.setFecha(date);

		date.setMonth(4);
		/* ADJU 5 */
		Parada parada5 = new Parada();
		parada5.setId(5L);
		parada5.setFecha(date);

		List<Parada> list = new ArrayList<>();
		list.add(parada5);
		list.add(parada4);

		Mockito.when(this.paradaRepository.findOne(parada.getId())).thenReturn(parada);
		Mockito.when(this.paradaRepository.findOne(parada2.getId())).thenReturn(parada2);
		Mockito.when(this.paradaRepository.findOne(parada3.getId())).thenReturn(parada3);
		Mockito.when(this.paradaRepository.findOne(parada4.getId())).thenReturn(parada4);
		Mockito.when(this.paradaRepository.findOne(parada5.getId())).thenReturn(parada5);
		date.setMonth(2);
		Mockito.when(this.paradaRepository.findAllByFechaGreaterThanEqualOrderByFecha(date)).thenReturn(list);
	}

	@Test
	public void whenFindParada() {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		date.setSeconds(0);

		Parada found_1 = this.paradaService.get(4L);
		Parada found_2 = this.paradaService.get(3L);
		Parada found_3 = this.paradaService.get(2L);
		Parada found_4 = this.paradaService.get(1L);

		assertThat(found_4.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_3.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_2.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_1.getFecha().getDate()).isEqualTo(date.getDate());
	}

	@Test
	@Transactional
	public void whenListParada() {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(2);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		date.setSeconds(0);

		List<Parada> listFound = this.paradaService.findAllByFechaOrderByFecha(date);
		Parada found_2 = this.paradaService.get(5L);

		assertSame(listFound.get(0), found_2);

	}
}
