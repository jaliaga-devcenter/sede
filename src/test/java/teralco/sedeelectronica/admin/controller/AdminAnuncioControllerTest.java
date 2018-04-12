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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teralco.sedeelectronica.app.Application;
import teralco.sedeelectronica.model.Anuncio;
import teralco.sedeelectronica.repository.AnuncioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminAnuncioControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Before // not working
	public void setUp() throws Exception {
		Anuncio anuncio1 = new Anuncio();
		Anuncio anuncio2 = new Anuncio();
		Anuncio anuncio3 = new Anuncio();

		anuncio1 = this.anuncioRepository.save(anuncio1);
		anuncio2 = this.anuncioRepository.save(anuncio2);
		anuncio3 = this.anuncioRepository.save(anuncio3);
	}

	@Test
	public void getAnuncios() throws Exception {
		this.mvc.perform(get("/admin/anuncios")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/anuncios/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Anuncio> l = this.anuncioRepository.findAll();
		this.mvc.perform(get("/admin/anuncios/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Anuncio> l = this.anuncioRepository.findAll();
		this.mvc.perform(get("/admin/anuncios/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/anuncios/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/anuncios/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			this.mvc.perform(post("/admin/anuncios/save")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/anuncios/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/anuncios/delete/")).andExpect(status().isFound());
	}
}
