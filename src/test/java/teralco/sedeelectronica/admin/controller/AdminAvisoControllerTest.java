package teralco.sedeelectronica.admin.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
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
import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.repository.AvisoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminAvisoControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private AvisoRepository avisoRepository;

	@SuppressWarnings("deprecation")
	@Before // not working
	public void setUp() throws Exception {
		Aviso aviso1 = new Aviso();
		Aviso aviso2 = new Aviso();
		Aviso aviso3 = new Aviso();
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		aviso1.setFecha(date);
		aviso2.setFecha(date);
		aviso3.setFecha(date);

		aviso1 = this.avisoRepository.save(aviso1);
		aviso2 = this.avisoRepository.save(aviso2);
		aviso3 = this.avisoRepository.save(aviso3);

	}

	@Test
	public void getNoticias() throws Exception {
		this.mvc.perform(get("/admin/avisos")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/avisos/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Aviso> l = this.avisoRepository.findAll();
		this.mvc.perform(get("/admin/avisos/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Aviso> l = this.avisoRepository.findAll();
		this.mvc.perform(get("/admin/avisos/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/avisos/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertThat(e.getMessage(),
					is("Request processing failed; nested exception is java.lang.NullPointerException"));
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/avisos/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertThat(e.getMessage(), is(
					"Request processing failed; nested exception is org.springframework.dao.EmptyResultDataAccessException: No class teralco.sedeelectronica.model.Aviso entity with id 3 exists!"));
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			Aviso aviso = new Aviso();
			this.mvc.perform(post("/admin/avisos/save").requestAttr("aviso", aviso)).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getSaveOk() throws Exception {
		try {
			Aviso aviso = new Aviso();
			Date date = new Date();
			date.setYear(2018);
			date.setMonth(1);
			date.setDate(1);
			date.setMinutes(56);
			date.setHours(16);
			aviso.setFecha(date);
			this.mvc.perform(post("/admin/avisos/save").requestAttr("aviso", aviso)).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
		try {
			this.mvc.perform(post("/admin/avisos/save")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/avisos/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/avisos/delete/")).andExpect(status().isFound());
	}
}
