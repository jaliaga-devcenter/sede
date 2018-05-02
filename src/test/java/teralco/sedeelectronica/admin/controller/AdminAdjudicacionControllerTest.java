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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.Application;
import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.repository.AdjudicacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminAdjudicacionControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AdjudicacionRepository adjudicacionRepository;

	@Before // not working
	public void setUp() throws Exception {
		Adjudicacion adju1 = new Adjudicacion();
		Adjudicacion adju2 = new Adjudicacion();
		Adjudicacion adju3 = new Adjudicacion();

		adju1 = this.adjudicacionRepository.save(adju1);
		adju2 = this.adjudicacionRepository.save(adju2);
		adju3 = this.adjudicacionRepository.save(adju3);

	}

	@Test
	public void getAdjudicaciones() throws Exception {
		this.mvc.perform(get("/admin/adjudicaciones")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/adjudicaciones/create")).andExpect(status().isOk());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/adjudicaciones/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getEditOk() throws Exception {
		List<Adjudicacion> l = this.adjudicacionRepository.findAll();
		this.mvc.perform(get("/admin/adjudicaciones/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Adjudicacion> l = this.adjudicacionRepository.findAll();
		this.mvc.perform(get("/admin/adjudicaciones/delete/" + l.get(l.size() - 1).getId()))
				.andExpect(status().isFound());
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/adjudicaciones/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		Adjudicacion adju = new Adjudicacion();
		MockMultipartFile file = new MockMultipartFile("file.pdf", "orig.pdf", null, "bar".getBytes());
		adju.setFileToUpload(file);
		this.mvc.perform(post("/admin/adjudicaciones/save").requestAttr("adjudicacion", adju))
				.andExpect(status().isFound());

	}

	@Test
	public void getSave2() throws Exception {
		this.mvc.perform(post("/admin/adjudicaciones/save")).andExpect(status().isFound());

	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/adjudicaciones/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/adjudicaciones/delete/")).andExpect(status().isFound());
	}
}
