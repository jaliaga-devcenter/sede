package teralco.sedeelectronica.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
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
public class ConoceSedeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getConoce() throws Exception {
		this.mvc.perform(get("/conoce-la-sede-electronica")).andExpect(status().isFound());
	}

	@Test
	public void getContenidos() throws Exception {
		this.mvc.perform(get("/contenidos-de-la-sede")).andExpect(status().isOk());
	}

	@Test
	public void getServicio() throws Exception {
		this.mvc.perform(get("/servicios-de-la-sede")).andExpect(status().isOk());
	}

	@Test
	public void getRegistro() throws Exception {
		this.mvc.perform(get("/registro-electronico")).andExpect(status().isOk());
	}

	@Test
	public void getFirma() throws Exception {
		this.mvc.perform(get("/firma-electronica-y-certificados-admitidos")).andExpect(status().isOk());
	}

	@Test
	public void getFecha() throws Exception {
		this.mvc.perform(get("/fecha-y-hora-oficial")).andExpect(status().isOk());
	}

	@Test
	@Ignore
	public void getCalendario() throws Exception {
		this.mvc.perform(get("/calendario-dias-inhabiles")).andExpect(status().isOk());
	}

	@Test
	@Ignore
	public void getSedes() throws Exception {
		this.mvc.perform(get("/sedes-fisicas")).andExpect(status().isOk());
	}

}
