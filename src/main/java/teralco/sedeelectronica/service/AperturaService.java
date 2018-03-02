package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.repository.AperturaRepository;

@Service
public class AperturaService {

	private AperturaRepository aperturaRepository;

	@Autowired
	public AperturaService(AperturaRepository pAperturaRepository) {
		this.aperturaRepository = pAperturaRepository;
	}

	public Iterable<Apertura> list() {
		return this.aperturaRepository.findAll();
	}

	public Apertura get(Long id) {
		return this.aperturaRepository.findOne(id);
	}

	// save
	public Apertura save(Apertura apertura) {
		return this.aperturaRepository.save(apertura);
	}

	public void delete(Long id) {
		this.aperturaRepository.delete(id);
	}

	// Pagination
	public Page<Apertura> listAllByPage(Pageable pageable) {
		return this.aperturaRepository.findAll(pageable);
	}
}
