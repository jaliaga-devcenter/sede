package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.repository.LenguajeRepository;

@Service
public class LenguajeService {

	private LenguajeRepository lenguajeRepository;

	@Autowired
	public LenguajeService(LenguajeRepository pLenguajeRepository) {
		this.lenguajeRepository = pLenguajeRepository;
	}

	public Iterable<Lenguaje> list() {
		return this.lenguajeRepository.findAll();
	}

	public Lenguaje get(Long id) {
		return this.lenguajeRepository.findOne(id);
	}

	// save
	public Lenguaje save(Lenguaje pLenguaje) {
		return this.lenguajeRepository.save(pLenguaje);
	}

	public void delete(Long id) {
		this.lenguajeRepository.delete(id);
	}

	// Custom
	public void deleteAll() {
		this.lenguajeRepository.deleteAll();
	}
}
