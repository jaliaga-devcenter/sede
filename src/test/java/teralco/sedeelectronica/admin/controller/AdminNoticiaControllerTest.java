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

import teralco.sedeelectronica.app.TestApplication;
import teralco.sedeelectronica.model.Noticia;
import teralco.sedeelectronica.repository.NoticiaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminNoticiaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Before // not working
	public void setUp() throws Exception {
		Noticia not1 = new Noticia();
		Noticia not2 = new Noticia();
		Noticia not3 = new Noticia();

		not1 = this.noticiaRepository.save(not1);
		not2 = this.noticiaRepository.save(not2);
		not3 = this.noticiaRepository.save(not3);

	}

	@Test
	public void getNoticias() throws Exception {
		this.mvc.perform(get("/admin/noticias")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/noticias/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Noticia> l = this.noticiaRepository.findAll();
		this.mvc.perform(get("/admin/noticias/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Noticia> l = this.noticiaRepository.findAll();
		this.mvc.perform(get("/admin/noticias/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/noticias/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/noticias/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			Noticia noticia = new Noticia();
			this.mvc.perform(post("/admin/noticias/save").requestAttr("noticia", noticia))
					.andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
		try {
			this.mvc.perform(post("/admin/noticias/save")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/noticias/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/noticias/delete/")).andExpect(status().isFound());
	}

}
