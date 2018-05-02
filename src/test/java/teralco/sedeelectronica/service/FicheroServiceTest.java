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
import teralco.sedeelectronica.model.Tipo;
import teralco.sedeelectronica.repository.FicheroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class FicheroServiceTest {
	@Autowired
	private FicheroService ficheroService;

	@MockBean
	private FicheroRepository ficheroRepository;

	@Before
	public void setUp() {
		// DECLARE VARIABLES
		String archivo = "cualquier.pdf";
		String uuid = "0123456789abcdefghijklmnopqrstuvwxyz";
		Fichero file = new Fichero();
		file.setTamanyo(512.24);
		file.setTipo(Tipo.PDF);
		file.setNombreOriginal(archivo);
		file.setUuid(uuid);
		file.setId(1L);

		Mockito.when(this.ficheroRepository.findOne(file.getId())).thenReturn(file);
		Mockito.when(this.ficheroRepository.save(file)).thenReturn(file);
		Mockito.when(this.ficheroRepository.findAll()).thenReturn(new ArrayList<Fichero>());
	}

	@Test
	public void whenFindFile() {
		Fichero found_1 = this.ficheroService.get(1L);
		assertThat(found_1.getTipo()).isNotEqualTo(Tipo.URL);
	}

	@Test
	public void whenList() {
		List<Fichero> found_2 = (List<Fichero>) this.ficheroService.list();
		assertThat(found_2.size()).isEqualTo(0);
	}

}
