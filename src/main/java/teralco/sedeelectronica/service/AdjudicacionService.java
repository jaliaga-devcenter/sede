package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.repository.AdjudicacionRepository;

@Service
public class AdjudicacionService {

	private AdjudicacionRepository adjudicacionRepository;

	@Autowired
	public AdjudicacionService(AdjudicacionRepository _adjudicacionRepository) {
		this.adjudicacionRepository = _adjudicacionRepository;
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
}
