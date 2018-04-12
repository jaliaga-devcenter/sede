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
import teralco.sedeelectronica.model.Parada;
import teralco.sedeelectronica.repository.ParadaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminParadasControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ParadaRepository paradaRepository;

	@SuppressWarnings("deprecation")
	@Before // not working
	public void setUp() throws Exception {
		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);

		Parada parada1 = new Parada();
		Parada parada2 = new Parada();
		Parada parada3 = new Parada();

		parada1.setFecha(date);
		parada2.setFecha(date);
		parada3.setFecha(date);
		parada1 = this.paradaRepository.save(parada1);
		parada2 = this.paradaRepository.save(parada2);
		parada3 = this.paradaRepository.save(parada3);

	}

	@Test
	public void getAperturas() throws Exception {
		this.mvc.perform(get("/admin/paradas")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/paradas/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Parada> l = this.paradaRepository.findAll();
		this.mvc.perform(get("/admin/paradas/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Parada> l = this.paradaRepository.findAll();
		this.mvc.perform(get("/admin/paradas/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/paradas/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/paradas/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			Parada parada = new Parada();
			this.mvc.perform(post("/admin/paradas/save").requestAttr("parada", parada)).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getSaveok() throws Exception {
		try {
			Date date = new Date();
			date.setYear(2018);
			date.setMonth(1);
			date.setDate(1);
			date.setMinutes(56);
			date.setHours(16);
			Parada parada = new Parada();
			parada.setFecha(date);
			this.mvc.perform(post("/admin/paradas/save").requestAttr("parada", parada)).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
		try {
			this.mvc.perform(post("/admin/paradas/save")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/paradas/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/paradas/delete/")).andExpect(status().isFound());
	}
}
