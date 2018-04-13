package teralco.sedeelectronica.controller;

import static org.junit.Assert.assertNotNull;
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
public class DownloadControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getDownload() throws Exception {
		this.mvc.perform(get("/download")).andExpect(status().isNotFound());
	}

	@Test
	public void getDownloadFile() throws Exception {
		try {
			this.mvc.perform(get("/download/YXNkZmFzZGZhZmRhc2RmQUZEc2FkU0FE")).andExpect(status().isNotFound());
		} catch (Exception ex) {
			assertNotNull(ex.getMessage());
		}
	}

	@Test
	public void getDownloadTablon() throws Exception {
		this.mvc.perform(get("/tablon-anuncios/download")).andExpect(status().isBadRequest());
	}

}
