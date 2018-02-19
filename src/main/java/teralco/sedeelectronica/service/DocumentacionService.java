package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.repository.DocumentacionRepository;

@Service
public class DocumentacionService {

	private DocumentacionRepository documentacionRespository;

	@Autowired
	public DocumentacionService(DocumentacionRepository documentacionRespository) {
		this.documentacionRespository = documentacionRespository;
	}

	public Iterable<Documentacion> list() {
		return documentacionRespository.findAll();
	}

	public Documentacion get(Long id) {
		return documentacionRespository.findOne(id);
	}

	// save
	public Documentacion save(Documentacion doc) {
		return documentacionRespository.save(doc);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		documentacionRespository.delete(id);
	}
}
