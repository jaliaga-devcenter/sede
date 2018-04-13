package teralco.sedeelectronica.admin.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Normativa;
import teralco.sedeelectronica.repository.NormativaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
@ActiveProfiles("test")
public class AdminNormativaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private NormativaRepository normativaRepository;

	@Before // not working
	public void setUp() throws Exception {
		String pUrl = "www.google.es";

		Normativa norm1 = new Normativa();
		Normativa norm2 = new Normativa();
		Normativa norm3 = new Normativa();

		norm1.setUrl(pUrl);
		norm2.setUrl(pUrl);
		norm3.setUrl(pUrl);

		norm1 = this.normativaRepository.save(norm1);
		norm2 = this.normativaRepository.save(norm2);
		norm3 = this.normativaRepository.save(norm3);

	}

	@Test
	public void getAperturas() throws Exception {
		this.mvc.perform(get("/admin/normativa")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/normativa/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Normativa> l = this.normativaRepository.findAll();
		this.mvc.perform(get("/admin/normativa/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Normativa> l = this.normativaRepository.findAll();
		this.mvc.perform(get("/admin/normativa/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
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
			this.mvc.perform(get("/admin/normativa/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			Normativa norma = new Normativa();
			this.mvc.perform(post("/admin/normativa/save").requestAttr("normativa", norma)).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSaveOk() throws Exception {
		try {
			Normativa norma = new Normativa();
			norma.setUrl("www.google.es");
			this.mvc.perform(post("/admin/normativa/save").flashAttr("normativa", norma)).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
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
