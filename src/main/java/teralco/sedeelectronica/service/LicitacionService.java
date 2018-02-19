package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.repository.LicitacionRepository;

@Service
public class LicitacionService {

	private LicitacionRepository licitacionRepository;

	@Autowired
	public LicitacionService(LicitacionRepository licitacionRepository) {
		this.licitacionRepository = licitacionRepository;
	}

	public Iterable<Licitacion> list() {
		return licitacionRepository.findAll();
	}

	public Licitacion get(Long id) {
		return licitacionRepository.findOne(id);
	}

	// save
	public Licitacion save(Licitacion lici) {
		return licitacionRepository.save(lici);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		licitacionRepository.delete(id);
	}
}
