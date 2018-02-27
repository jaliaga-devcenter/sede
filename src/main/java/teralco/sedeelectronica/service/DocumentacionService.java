package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.repository.DocumentacionRepository;

@Service
public class DocumentacionService {

	private DocumentacionRepository documentacionRespository;

	@Autowired
	public DocumentacionService(DocumentacionRepository pDocumentacionRespository) {
		this.documentacionRespository = pDocumentacionRespository;
	}

	public Iterable<Documentacion> list() {
		return this.documentacionRespository.findAll();
	}

	public Documentacion get(Long id) {
		return this.documentacionRespository.findOne(id);
	}

	// save
	public Documentacion save(Documentacion doc) {
		return this.documentacionRespository.save(doc);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.documentacionRespository.delete(id);
	}

	// Pagination
	public Page<Documentacion> listAllByPage(Pageable pageable) {
		return this.documentacionRespository.findAll(pageable);
	}
}
