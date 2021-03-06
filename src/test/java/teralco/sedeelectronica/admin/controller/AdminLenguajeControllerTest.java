package teralco.sedeelectronica.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import teralco.sedeelectronica.app.Application;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminLenguajeControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;

	@Autowired
	private MockMvc mvc;

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void init() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	}

	@Test
	public void getCreateOk() throws Exception {
		String[] list = { "es - Castellano" };
		this.mvc.perform(post("/admin/lenguajes/create").content(this.mapper.writeValueAsString(list))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
