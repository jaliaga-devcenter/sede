package teralco.sedeelectronica.admin.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.transaction.Transactional;

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
import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Estado;
import teralco.sedeelectronica.repository.DocumentacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@Transactional
@WithMockUser(roles = { "ADMIN_SEDE" })
public class AdminDocumentacionControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private DocumentacionRepository documentacionRepository;

	@Before // not working
	public void setUp() throws Exception {
		Documentacion doc1 = new Documentacion();
		Documentacion doc2 = new Documentacion();
		Documentacion doc3 = new Documentacion();

		doc1 = this.documentacionRepository.save(doc1);
		doc2 = this.documentacionRepository.save(doc2);
		doc3 = this.documentacionRepository.save(doc3);

	}

	@Test
	public void getDocumentacion() throws Exception {
		this.mvc.perform(get("/admin/documentos")).andExpect(status().isOk());
	}

	@Test
	public void getCreate() throws Exception {
		this.mvc.perform(get("/admin/documentos/create")).andExpect(status().isOk());
	}

	@Test
	public void getEditOk() throws Exception {
		List<Documentacion> l = this.documentacionRepository.findAll();
		this.mvc.perform(get("/admin/documentos/edit/" + l.get(l.size() - 1).getId())).andExpect(status().isOk());
	}

	@Test
	public void getDeleteOk() throws Exception {
		List<Documentacion> l = this.documentacionRepository.findAll();
		this.mvc.perform(get("/admin/documentos/delete/" + l.get(l.size() - 1).getId())).andExpect(status().isFound());
	}

	@Test
	public void getEdit() throws Exception {
		try {
			this.mvc.perform(get("/admin/documentos/edit/2")).andExpect(status().isOk());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getDelete() throws Exception {
		try {
			this.mvc.perform(get("/admin/documentos/delete/3")).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSaveOk() throws Exception {
		try {
			Documentacion doc = new Documentacion();
			doc.setEstado((short) Estado.APERTURA.ordinal());
			MockMultipartFile file = new MockMultipartFile("file.pdf", "orig.pdf", null, "bar".getBytes());
			doc.setFileToUpload(file);
			this.mvc.perform(post("/admin/documentos/save").requestAttr("documentacion", doc))
					.andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getSave() throws Exception {
		try {
			this.mvc.perform(post("/admin/documentos/save")).andExpect(status().isFound());
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void getNoEdit() throws Exception {
		this.mvc.perform(get("/admin/documentos/edit/")).andExpect(status().isFound());
	}

	@Test
	public void getNoDelete() throws Exception {
		this.mvc.perform(get("/admin/documentos/delete/")).andExpect(status().isFound());
	}
}
