package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
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
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.repository.LicitacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class LicitacionServiceTest {

	@Autowired
	private LicitacionService licitacionService;

	@MockBean
	private LicitacionRepository licitacionRepository;

	@Before
	public void setUp() {
		/* ADJU 1 */
		Licitacion lici = new Licitacion();
		lici.setId(1L);
		BigDecimal bd = new BigDecimal(1024.5);
		lici.setPresupuesto(bd);
		/* ADJU 2 */
		Licitacion lici2 = new Licitacion();
		lici2.setId(2L);
		BigDecimal bd2 = new BigDecimal(512.6);
		lici2.setPresupuesto(bd2);
		/* ADJU 3 */
		Licitacion lici3 = new Licitacion();
		lici3.setId(3L);
		BigDecimal bd3 = new BigDecimal(256.4);
		lici3.setPresupuesto(bd3);
		/* ADJU 4 */
		Licitacion lici4 = new Licitacion();
		lici4.setId(4L);
		BigDecimal bd4 = new BigDecimal(128);
		lici4.setPresupuesto(bd4);

		List<Licitacion> listN = new ArrayList<>();
		listN.add(lici);
		listN.add(lici2);
		listN.add(lici3);
		listN.add(lici4);

		Mockito.when(this.licitacionRepository.findOne(lici.getId())).thenReturn(lici);
		Mockito.when(this.licitacionRepository.findOne(lici2.getId())).thenReturn(lici2);
		Mockito.when(this.licitacionRepository.findOne(lici3.getId())).thenReturn(lici3);
		Mockito.when(this.licitacionRepository.findOne(lici4.getId())).thenReturn(lici4);
		Mockito.when(this.licitacionRepository.findAll()).thenReturn(listN);

	}

	@Test
	public void whenFindLici() {
		BigDecimal bd = new BigDecimal(1024.5);
		BigDecimal bd2 = new BigDecimal(512.6);
		BigDecimal bd3 = new BigDecimal(256.4);
		BigDecimal bd4 = new BigDecimal(128);

		Licitacion found_1 = this.licitacionService.get(4L);
		Licitacion found_2 = this.licitacionService.get(3L);
		Licitacion found_3 = this.licitacionService.get(2L);
		Licitacion found_4 = this.licitacionService.get(1L);
		List<Licitacion> list = (List<Licitacion>) this.licitacionService.list();

		assertThat(found_4.getPresupuesto()).isEqualTo(bd);
		assertThat(found_3.getPresupuesto()).isEqualTo(bd2);
		assertThat(found_2.getPresupuesto()).isEqualTo(bd3);
		assertThat(found_1.getPresupuesto()).isEqualTo(bd4);

		assertThat(list.size()).isEqualTo(4);
	}
}
