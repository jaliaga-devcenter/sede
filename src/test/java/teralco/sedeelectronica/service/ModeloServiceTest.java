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
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.repository.ModeloRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class ModeloServiceTest {

	@Autowired
	private ModeloService modeloService;

	@MockBean
	private ModeloRepository modeloRepository;

	@Before
	public void setUp() {
		Fichero file = new Fichero();
		file.setTamanyo(78.9);
		/* ADJU 1 */
		Modelo mode = new Modelo();
		mode.setId(1L);
		mode.setFichero(file);
		/* ADJU 2 */
		Modelo mode2 = new Modelo();
		mode2.setId(2L);
		mode2.setFichero(file);
		/* ADJU 3 */
		Modelo mode3 = new Modelo();
		mode3.setId(3L);
		mode3.setFichero(file);

		/* ADJU 4 */
		Modelo mode4 = new Modelo();
		mode4.setId(4L);
		mode4.setFichero(file);

		List<Modelo> listN = new ArrayList<>();
		listN.add(mode);
		listN.add(mode2);
		listN.add(mode3);
		listN.add(mode4);

		Mockito.when(this.modeloRepository.findOne(mode.getId())).thenReturn(mode);
		Mockito.when(this.modeloRepository.findOne(mode2.getId())).thenReturn(mode2);
		Mockito.when(this.modeloRepository.findOne(mode3.getId())).thenReturn(mode3);
		Mockito.when(this.modeloRepository.findOne(mode4.getId())).thenReturn(mode4);
		Mockito.when(this.modeloRepository.findAll()).thenReturn(listN);

	}

	@Test
	public void whenFindModelo() {
		Modelo found_1 = this.modeloService.get(4L);
		Modelo found_2 = this.modeloService.get(3L);
		Modelo found_3 = this.modeloService.get(2L);
		Modelo found_4 = this.modeloService.get(1L);

		assertThat(found_4.getFichero().getTamanyo()).isEqualTo(78.9);
		assertThat(found_3.getFichero().getTamanyo()).isEqualTo(78.9);
		assertThat(found_2.getFichero().getTamanyo()).isEqualTo(78.9);
		assertThat(found_1.getFichero().getTamanyo()).isEqualTo(78.9);
	}

	@Test
	public void whenList() {
		List<Modelo> listM = (List<Modelo>) this.modeloService.list();
		assertThat(listM.size()).isEqualTo(4);
	}
}
