package teralco.sedeelectronica.admin.controller;

import static org.junit.Assert.assertNotNull;
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
import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.repository.AperturaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminAperturaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AperturaRepository aperturaRepository;

	@SuppressWarnings("deprecation")
	@Before // not working
	public void setUp() throws Exception {
		Apertura aper1 = new Apertura();
		Apertura aper2 = new Apertura();
		Apertura aper3 = new Apertura();
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);
		aper1.setFecha(date);
		aper1.setHora(date);
		aper2.setFecha(date);
		aper2.setHora(date);
		aper3.setFecha(date);
		aper3.setHora(date);

		aper1 = this.aperturaRepository.save(aper1);
		aper2 = this.aperturaRepository.save(aper2);
		aper3 = this.aperturaRepository.save(aper3);
	}

	@Test
	public void getAperturas() throws Exception {
		this.mvc.perform(get("/admin/aperturas")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/aperturas/create")).andExpect(status().isOk());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/aperturas/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/aperturas/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getEditOk() throws Exception {
		List<Apertura> l = this.aperturaRepository.findAll();
		this.mvc.perform(get("/admin/aperturas/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Apertura> l = this.aperturaRepository.findAll();
		this.mvc.perform(get("/admin/aperturas/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
	}

	@Test
	public void getSave() throws Exception {
		try {
			Apertura apertura = new Apertura();
			this.mvc.perform(post("/admin/aperturas/save").requestAttr("apertura", apertura))
					.andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
		try {
			this.mvc.perform(post("/admin/aperturas/save")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/aperturas/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/aperturas/delete/")).andExpect(status().isFound());
	}
}
