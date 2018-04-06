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
public class AnuncioControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getAnuncios() throws Exception {
		this.mvc.perform(get("/tablon-anuncios")).andExpect(status().isOk());
	}

	@Test
	@Ignore
	public void getAnuncio() throws Exception {
		this.mvc.perform(get("/tablon-anuncios/2")).andExpect(status().isOk());
	}
}
