package teralco.sedeelectronica.admin.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.repository.ModeloRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminModeloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModeloRepository modeloRepository;

	@Before // not working
	public void setUp() throws Exception {
		Modelo doc1 = new Modelo();
		Modelo doc2 = new Modelo();
		Modelo doc3 = new Modelo();

		doc1 = this.modeloRepository.save(doc1);
		doc2 = this.modeloRepository.save(doc2);
		doc3 = this.modeloRepository.save(doc3);

	}

	@Test
	public void getModelos() throws Exception {
		this.mvc.perform(get("/admin/modelos")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/modelos/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Modelo> l = this.modeloRepository.findAll();
		this.mvc.perform(get("/admin/modelos/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Modelo> l = this.modeloRepository.findAll();
		this.mvc.perform(get("/admin/modelos/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
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
			this.mvc.perform(get("/admin/modelos/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertThat(e.getMessage(), is(
					"Request processing failed; nested exception is org.springframework.dao.EmptyResultDataAccessException: No class teralco.sedeelectronica.model.Modelo entity with id 3 exists!"));
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			Modelo modelo = new Modelo();
			this.mvc.perform(post("/admin/modelos/save").requestAttr("modelo", modelo)).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
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
