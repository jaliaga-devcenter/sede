package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.repository.LicitacionRepository;

@Service
public class LicitacionService {

	private LicitacionRepository licitacionRepository;

	@Autowired
	public LicitacionService(LicitacionRepository pLicitacionRepository) {
		this.licitacionRepository = pLicitacionRepository;
	}

	public Iterable<Licitacion> list() {
		return this.licitacionRepository.findAll();
	}

	public Licitacion get(Long id) {
		return this.licitacionRepository.findOne(id);
	}

	// save
	public Licitacion save(Licitacion lici) {
		return this.licitacionRepository.save(lici);
	}

	public void delete(Long id) {
		this.licitacionRepository.delete(id);
	}

	// Pagination
	public Page<Licitacion> listAllByPage(Pageable pageable) {
		return this.licitacionRepository.findAll(pageable);
	}
}
