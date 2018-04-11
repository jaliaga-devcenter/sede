package teralco.sedeelectronica.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
public class PerfilContratanteControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getPerfilDelContratante() throws Exception {
		this.mvc.perform(get("/perfil-del-contratante")).andExpect(status().isFound());
	}

	@Test
	public void getLicitaciones() throws Exception {
		this.mvc.perform(get("/licitaciones")).andExpect(status().isOk());
	}

	@Test
	public void getAperturas() throws Exception {
		this.mvc.perform(get("/aperturas")).andExpect(status().isOk());
	}

	@Test
	public void getAdjudicaciones() throws Exception {
		this.mvc.perform(get("/adjudicaciones")).andExpect(status().isOk());
	}

	@Test
	public void getDocumentos() throws Exception {
		this.mvc.perform(get("/documentos")).andExpect(status().isOk());
	}

	@Test
	public void getModelos() throws Exception {
		this.mvc.perform(get("/modelos")).andExpect(status().isOk());
	}

	@Test
	public void getAvisos() throws Exception {
		this.mvc.perform(get("/avisos")).andExpect(status().isOk());
	}

}
