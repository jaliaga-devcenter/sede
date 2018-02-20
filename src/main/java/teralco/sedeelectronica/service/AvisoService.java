package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.repository.AvisoRepository;

@Service
public class AvisoService {

	private AvisoRepository avisoRepository;

	@Autowired
	public AvisoService(AvisoRepository avisoRepository) {
		this.avisoRepository = avisoRepository;
	}

	public Iterable<Aviso> list() {
		return avisoRepository.findAll();
	}

	public Aviso get(Long id) {
		return avisoRepository.findOne(id);
	}

	// save
	public Aviso save(Aviso aviso) {
		return avisoRepository.save(aviso);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		avisoRepository.delete(id);
	}
}
