package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.repository.AdjudicacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class AdjudicacionServiceTest {

	@Autowired
	private AdjudicacionService adjudicacionService;

	@MockBean
	private AdjudicacionRepository adjudicacionRepository;

	@Before
	public void setUp() {
		/* ADJU 1 */
		Adjudicacion adju = new Adjudicacion();
		adju.setId(1L);
		BigDecimal bd = new BigDecimal(1024.5);
		adju.setPresupuesto(bd);
		/* ADJU 2 */
		Adjudicacion adju2 = new Adjudicacion();
		adju2.setId(2L);
		BigDecimal bd2 = new BigDecimal(512.6);
		adju2.setPresupuesto(bd2);
		/* ADJU 3 */
		Adjudicacion adju3 = new Adjudicacion();
		adju3.setId(3L);
		BigDecimal bd3 = new BigDecimal(256.4);
		adju3.setPresupuesto(bd3);
		/* ADJU 4 */
		Adjudicacion adju4 = new Adjudicacion();
		adju4.setId(4L);
		BigDecimal bd4 = new BigDecimal(128);
		adju4.setPresupuesto(bd4);

		Mockito.when(this.adjudicacionRepository.findOne(adju.getId())).thenReturn(adju);
		Mockito.when(this.adjudicacionRepository.findOne(adju2.getId())).thenReturn(adju2);
		Mockito.when(this.adjudicacionRepository.findOne(adju3.getId())).thenReturn(adju3);
		Mockito.when(this.adjudicacionRepository.findOne(adju4.getId())).thenReturn(adju4);

	}

	@Test
	public void whenFindAdju() {
		BigDecimal bd = new BigDecimal(1024.5);
		BigDecimal bd2 = new BigDecimal(512.6);
		BigDecimal bd3 = new BigDecimal(256.4);
		BigDecimal bd4 = new BigDecimal(128);

		Adjudicacion found_1 = this.adjudicacionService.get(4L);
		Adjudicacion found_2 = this.adjudicacionService.get(3L);
		Adjudicacion found_3 = this.adjudicacionService.get(2L);
		Adjudicacion found_4 = this.adjudicacionService.get(1L);

		assertThat(found_4.getPresupuesto()).isEqualTo(bd);
		assertThat(found_3.getPresupuesto()).isEqualTo(bd2);
		assertThat(found_2.getPresupuesto()).isEqualTo(bd3);
		assertThat(found_1.getPresupuesto()).isEqualTo(bd4);
	}
}
