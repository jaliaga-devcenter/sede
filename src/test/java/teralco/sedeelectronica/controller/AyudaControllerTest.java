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
public class AyudaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getAccesibilidad() throws Exception {
		this.mvc.perform(get("/accesibilidad")).andExpect(status().isOk());
	}

	@Test
	public void getObtener() throws Exception {
		this.mvc.perform(get("/obtener-certificado")).andExpect(status().isOk());
	}

	@Test
	public void getQuejas() throws Exception {
		this.mvc.perform(get("/quejas-sugerencias")).andExpect(status().isOk());
	}

	@Test
	public void getInstrucciones() throws Exception {
		this.mvc.perform(get("/instrucciones")).andExpect(status().isOk());
	}

	@Test
	public void getRequisitos() throws Exception {
		this.mvc.perform(get("/requisitos-tecnicos")).andExpect(status().isOk());
	}

	@Test
	public void getAviso() throws Exception {
		this.mvc.perform(get("/aviso-legal")).andExpect(status().isOk());
	}

	@Test
	public void getPoliticas() throws Exception {
		this.mvc.perform(get("/politica-cookies")).andExpect(status().isOk());
	}

	@Test
	public void getMapa() throws Exception {
		this.mvc.perform(get("/mapa-web")).andExpect(status().isOk());
	}

}
