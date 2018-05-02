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
import teralco.sedeelectronica.model.Contacto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
public class ContactoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getContacto() throws Exception {
		this.mvc.perform(get("/contacto")).andExpect(status().isOk());
	}

	@Test
	public void getContactoSend() throws Exception {
		this.mvc.perform(get("/contacto/send")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void getContactoSend2() throws Exception {
		Contacto con = new Contacto();
		this.mvc.perform(post("/contacto/send").flashAttr("contacto", con).param("g-recaptcha-response", ""))
				.andExpect(status().isOk());
	}

	@Test
	public void getContactoSend3() throws Exception {
		Contacto con = new Contacto();
		con.setComentario("asdfasdfsdaf");
		con.setEmail("1234@345.es");
		con.setNombre("nommmbre");
		this.mvc.perform(post("/contacto/send").flashAttr("contacto", con).param("g-recaptcha-response", ""))
				.andExpect(status().isOk());
	}

}
