package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.repository.AvisoRepository;

@Service
public class AvisoService {

	private AvisoRepository avisoRepository;

	@Autowired

	public AvisoService(AvisoRepository _avisoRepository) {
		this.avisoRepository = _avisoRepository;
	}

	public Iterable<Aviso> list() {
		return this.avisoRepository.findAll();
	}

	public Aviso get(Long id) {
		return this.avisoRepository.findOne(id);
	}

	// save
	public Aviso save(Aviso aviso) {

		return this.avisoRepository.save(aviso);
	}

	public void delete(Long id) {
		this.avisoRepository.delete(id);
	}
}
