package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Normativa;
import teralco.sedeelectronica.repository.NormativaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class NormativaServiceTest {

	@Autowired
	private NormativaService normativaService;

	@MockBean
	private NormativaRepository normativaRepository;

	@Before
	public void setUp() {
		String url1 = "www.url1.com";
		String url2 = "www.url2.com";
		String url3 = "www.url3.com";
		String url4 = "www.url4.com";

		/* ADJU 1 */
		Normativa norma = new Normativa();
		norma.setId(1L);
		norma.setUrl(url1);
		/* ADJU 2 */
		Normativa norma2 = new Normativa();
		norma2.setId(2L);
		norma2.setUrl(url2);
		/* ADJU 3 */
		Normativa norma3 = new Normativa();
		norma3.setId(3L);
		norma3.setUrl(url3);
		/* ADJU 4 */
		Normativa norma4 = new Normativa();
		norma4.setId(4L);
		norma4.setUrl(url4);

		Mockito.when(this.normativaRepository.findOne(norma.getId())).thenReturn(norma);
		Mockito.when(this.normativaRepository.findOne(norma2.getId())).thenReturn(norma2);
		Mockito.when(this.normativaRepository.findOne(norma3.getId())).thenReturn(norma3);
		Mockito.when(this.normativaRepository.findOne(norma4.getId())).thenReturn(norma4);

	}

	@Test
	public void whenFindNorma() {
		String url1 = "www.url1.com";
		String url2 = "www.url2.com";
		String url3 = "www.url3.com";
		String url4 = "www.url4.com";

		Normativa found_1 = this.normativaService.get(4L);
		Normativa found_2 = this.normativaService.get(3L);
		Normativa found_3 = this.normativaService.get(2L);
		Normativa found_4 = this.normativaService.get(1L);

		assertThat(found_4.getUrl()).isEqualTo(url1);
		assertThat(found_3.getUrl()).isEqualTo(url2);
		assertThat(found_2.getUrl()).isEqualTo(url3);
		assertThat(found_1.getUrl()).isEqualTo(url4);
	}
}
