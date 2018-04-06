package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.repository.AvisoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
@SuppressWarnings("deprecation")
public class AvisoServiceTest {

	@Autowired
	private AvisoService avisoService;

	@MockBean
	private AvisoRepository avisoRepository;

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
		Aviso aviso = new Aviso();
		aviso.setId(1L);
		aviso.setFecha(date);
		/* ADJU 2 */
		Aviso aviso2 = new Aviso();
		aviso2.setId(2L);
		aviso2.setFecha(date);
		/* ADJU 3 */
		Aviso aviso3 = new Aviso();
		aviso3.setId(3L);
		aviso3.setFecha(date);
		/* ADJU 4 */
		Aviso aviso4 = new Aviso();
		aviso4.setId(4L);
		aviso4.setFecha(date);

		List<Aviso> listN = new ArrayList<>();
		listN.add(aviso);
		listN.add(aviso4);

		Mockito.when(this.avisoRepository.findOne(aviso.getId())).thenReturn(aviso);
		Mockito.when(this.avisoRepository.findOne(aviso2.getId())).thenReturn(aviso2);
		Mockito.when(this.avisoRepository.findOne(aviso3.getId())).thenReturn(aviso3);
		Mockito.when(this.avisoRepository.findOne(aviso4.getId())).thenReturn(aviso4);
		Mockito.when(this.avisoRepository.findAll()).thenReturn(listN);
	}

	@Test
	public void whenFindAviso() {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		date.setSeconds(0);

		Aviso found_1 = this.avisoService.get(4L);
		Aviso found_2 = this.avisoService.get(3L);
		Aviso found_3 = this.avisoService.get(2L);
		Aviso found_4 = this.avisoService.get(1L);

		assertThat(found_4.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_3.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_2.getFecha().getDate()).isEqualTo(date.getDate());
		assertThat(found_1.getFecha().getDate()).isEqualTo(date.getDate());

		List<Aviso> list = (List<Aviso>) this.avisoService.list();

		assertThat(list.size()).isEqualTo(2);

	}
}
