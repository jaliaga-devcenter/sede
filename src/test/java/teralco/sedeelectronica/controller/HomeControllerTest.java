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
public class HomeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getTest() throws Exception {
		this.mvc.perform(get("/test")).andExpect(status().isOk());
	}

	@Test
	public void getHome() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void getCat() throws Exception {
		this.mvc.perform(get("/categorias")).andExpect(status().isOk());
	}

	@Test
	public void getBuscador() throws Exception {
		this.mvc.perform(get("/buscador-procedimientos")).andExpect(status().isOk());
	}

	@Test
	public void getBuscador2() throws Exception {
		this.mvc.perform(get("/buscar-procedimientos")).andExpect(status().isOk());
	}

	@Test
	public void getFicha() throws Exception {
		this.mvc.perform(get("/ficha-procedimiento/")).andExpect(status().isNotFound());
	}

	@Test
	public void getProce() throws Exception {
		this.mvc.perform(get("/procedimiento")).andExpect(status().isNotFound());
	}

	@Test
	public void getFicha2() throws Exception {
		this.mvc.perform(get("/ficha-procedimiento/103")).andExpect(status().isOk());
	}

	@Test
	public void getProce2() throws Exception {
		this.mvc.perform(get("/procedimiento/1")).andExpect(status().isFound());
	}

	@Test
	public void getLogout() throws Exception {
		this.mvc.perform(get("/logout")).andExpect(status().isFound());
	}

}
