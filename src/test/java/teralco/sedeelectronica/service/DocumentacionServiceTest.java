package teralco.sedeelectronica.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Estado;
import teralco.sedeelectronica.repository.DocumentacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class DocumentacionServiceTest {

	@Autowired
	private DocumentacionService documentacionService;

	@MockBean
	private DocumentacionRepository documentacionRepository;

	@Before
	public void setUp() {
		/* ADJU 1 */
		Documentacion docu = new Documentacion();
		docu.setId(1L);
		docu.setEstado((short) Estado.ADJUDICACION.ordinal());
		/* ADJU 2 */
		Documentacion docu2 = new Documentacion();
		docu2.setId(2L);
		docu2.setEstado((short) Estado.APERTURA.ordinal());
		/* ADJU 3 */
		Documentacion docu3 = new Documentacion();
		docu3.setId(3L);
		docu3.setEstado((short) Estado.CONTRATADO.ordinal());
		/* ADJU 4 */
		Documentacion docu4 = new Documentacion();
		docu4.setId(4L);
		docu4.setEstado((short) Estado.ADJUDICACION.ordinal());

		List<Documentacion> listN = new ArrayList<>();
		listN.add(docu);
		listN.add(docu3);
		listN.add(docu4);

		Mockito.when(this.documentacionRepository.findOne(docu.getId())).thenReturn(docu);
		Mockito.when(this.documentacionRepository.findOne(docu2.getId())).thenReturn(docu2);
		Mockito.when(this.documentacionRepository.findOne(docu3.getId())).thenReturn(docu3);
		Mockito.when(this.documentacionRepository.findOne(docu4.getId())).thenReturn(docu4);
		Mockito.when(this.documentacionRepository.findAll()).thenReturn(listN);

	}

	@Test
	public void whenFindDocu() {
		Documentacion found_1 = this.documentacionService.get(4L);
		Documentacion found_2 = this.documentacionService.get(3L);
		Documentacion found_3 = this.documentacionService.get(2L);
		Documentacion found_4 = this.documentacionService.get(1L);

		assertThat(found_4.getEstado()).isEqualTo((short) Estado.ADJUDICACION.ordinal());
		assertThat(found_3.getEstado()).isEqualTo((short) Estado.APERTURA.ordinal());
		assertThat(found_2.getEstado()).isEqualTo((short) Estado.CONTRATADO.ordinal());
		assertThat(found_1.getEstado()).isEqualTo((short) Estado.ADJUDICACION.ordinal());
	}

	@Test
	public void whenList() {
		List<Documentacion> list = (List<Documentacion>) this.documentacionService.list();
		assertThat(list.size()).isEqualTo(3);
	}
}
