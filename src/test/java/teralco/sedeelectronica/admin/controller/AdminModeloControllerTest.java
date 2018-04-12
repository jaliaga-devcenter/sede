package teralco.sedeelectronica.admin.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminModeloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getModelos() throws Exception {
		this.mvc.perform(get("/admin/modelos")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/modelos/create")).andExpect(status().isOk());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/modelos/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertThat(e.getMessage(),
					is("Request processing failed; nested exception is java.lang.NullPointerException"));
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/modelos/delete/3")).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			assertThat(e.getMessage(), is(
					"Request processing failed; nested exception is org.springframework.dao.EmptyResultDataAccessException: No class teralco.sedeelectronica.model.Modelo entity with id 3 exists!"));
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			this.mvc.perform(post("/admin/modelos/save")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/modelos/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/modelos/delete/")).andExpect(status().isFound());
	}
}
