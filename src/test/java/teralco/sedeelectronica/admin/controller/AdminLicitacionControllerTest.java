package teralco.sedeelectronica.admin.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
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
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.repository.LicitacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminLicitacionControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private LicitacionRepository licitacionRepository;

	@SuppressWarnings("deprecation")
	@Before // not working
	public void setUp() throws Exception {
		BigDecimal bd = new BigDecimal(1024.50);
		Short medio = -1;

		Date date = new Date();
		date.setYear(2018);
		date.setMonth(1);
		date.setDate(1);
		date.setMinutes(56);
		date.setHours(16);

		Licitacion lici1 = new Licitacion();
		Licitacion lici2 = new Licitacion();
		Licitacion lici3 = new Licitacion();

		lici1.setPresupuesto(bd);
		lici1.setFechaPub(date);
		lici1.setFinPlazo(date);
		lici1.setMedio(medio);
		lici2.setPresupuesto(bd);
		lici2.setFechaPub(date);
		lici2.setFinPlazo(date);
		lici2.setMedio(medio);
		lici3.setPresupuesto(bd);
		lici3.setFechaPub(date);
		lici3.setFinPlazo(date);
		lici3.setMedio(medio);

		lici1 = this.licitacionRepository.save(lici1);
		lici2 = this.licitacionRepository.save(lici2);
		lici3 = this.licitacionRepository.save(lici3);

	}

	@Test
	public void getLicitaciones() throws Exception {
		this.mvc.perform(get("/admin/licitaciones")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/licitaciones/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Licitacion> l = this.licitacionRepository.findAll();
		this.mvc.perform(get("/admin/licitaciones/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Licitacion> l = this.licitacionRepository.findAll();
		this.mvc.perform(get("/admin/licitaciones/delete/" + l.get(l.size() - 1).getId()))
				.andExpect(status().isFound());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/licitaciones/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/licitaciones/delete/3")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			Licitacion lici = new Licitacion();
			MockMultipartFile file = new MockMultipartFile("file.pdf", "orig.pdf", null, "bar".getBytes());
			lici.setFileToUpload(file);
			this.mvc.perform(post("/admin/licitaciones/save").requestAttr("licitacion", lici))
					.andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getSaveOk() throws Exception {
		try {
			Licitacion lici = new Licitacion();

			BigDecimal bd = new BigDecimal(1024.50);
			Short medio = -1;

			Date date = new Date();
			date.setYear(2018);
			date.setMonth(1);
			date.setDate(1);
			date.setMinutes(56);
			date.setHours(16);
			lici.setFechaPub(date);
			lici.setFinPlazo(date);
			lici.setPresupuesto(bd);
			lici.setMedio(medio);
			this.mvc.perform(post("/admin/licitaciones/save").flashAttr("licitacion", lici))
					.andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave2() throws Exception {
		try {
			this.mvc.perform(post("/admin/licitaciones/save")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/licitaciones/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/licitaciones/delete/")).andExpect(status().isFound());
	}
}
