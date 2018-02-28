package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.repository.AdjudicacionRepository;

@Service
public class AdjudicacionService {

	private AdjudicacionRepository adjudicacionRepository;

	@Autowired
	public AdjudicacionService(AdjudicacionRepository pAdjudicacionRepository) {
		this.adjudicacionRepository = pAdjudicacionRepository;
	}

	public Iterable<Adjudicacion> list() {
		return this.adjudicacionRepository.findAll();
	}

	public Adjudicacion get(Long id) {
		return this.adjudicacionRepository.findOne(id);
	}

	// save
	public Adjudicacion save(Adjudicacion adjudicacion) {
		return this.adjudicacionRepository.save(adjudicacion);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.adjudicacionRepository.delete(id);
	}

	// Pagination
	public Page<Adjudicacion> listAllByPage(Pageable pageable) {
		return this.adjudicacionRepository.findAll(pageable);
	}
}
