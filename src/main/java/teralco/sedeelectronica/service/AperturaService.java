package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.repository.AperturaRepository;

@Service
public class AperturaService {

	private AperturaRepository aperturaRepository;

	@Autowired
	public AperturaService(AperturaRepository aperturaRepository) {
		this.aperturaRepository = aperturaRepository;
	}

	public Iterable<Apertura> list() {
		return aperturaRepository.findAll();
	}

	public Apertura get(Long id) {
		return aperturaRepository.findOne(id);
	}

	// save
	public Apertura save(Apertura apertura) {
		return aperturaRepository.save(apertura);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		aperturaRepository.delete(id);
	}
}
