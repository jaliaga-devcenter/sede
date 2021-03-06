package teralco.sedeelectronica.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.CSVValidation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
public class VerifirmaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getVerifirma() throws Exception {
		this.mvc.perform(get("/verifirma")).andExpect(status().isOk());
	}

	@Test
	public void getVerifirmaSend() throws Exception {
		CSVValidation csv = new CSVValidation();
		csv.setCsv("asdfasfwqwerasdf");
		this.mvc.perform(post("/verifirma/send").flashAttr("CSVValidation", csv).param("g-recaptcha-response", ""))
				.andExpect(status().isOk());
	}
}
