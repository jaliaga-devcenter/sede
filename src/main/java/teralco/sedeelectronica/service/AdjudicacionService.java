package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.repository.AdjudicacionRepository;

@Service
public class AdjudicacionService {

	private AdjudicacionRepository adjudicacionRepository;

	@Autowired
	public AdjudicacionService(AdjudicacionRepository adjudicacionRepository) {
		this.adjudicacionRepository = adjudicacionRepository;
	}

	public Iterable<Adjudicacion> list() {
		return adjudicacionRepository.findAll();
	}

	public Adjudicacion get(Long id) {
		return adjudicacionRepository.findOne(id);
	}

	// save
	public Adjudicacion save(Adjudicacion adjudicacion) {
		return adjudicacionRepository.save(adjudicacion);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		adjudicacionRepository.delete(id);
	}
}
