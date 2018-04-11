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
public class AccesoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getAcesso() throws Exception {
		this.mvc.perform(get("/acceso")).andExpect(status().isOk());
	}

	@Test
	public void getLogin() throws Exception {
		this.mvc.perform(get("/login")).andExpect(status().isOk());
	}

}
