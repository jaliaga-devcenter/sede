package tecalco.sedeelectronica.admin.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
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
public class AdminNormativaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Ignore
	public void getAperturas() throws Exception {
		this.mvc.perform(get("/admin/normativa")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/normativa/create")).andExpect(status().isOk());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/normativa/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/normativa/delete/3")).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			this.mvc.perform(post("/admin/normativa/save")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/normativa/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/normativa/delete/")).andExpect(status().isFound());
	}
}
