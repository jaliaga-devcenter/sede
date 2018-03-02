package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.repository.FicheroRepository;

@Service
public class FicheroService {
	private FicheroRepository ficheroRepository;

	@Autowired
	public FicheroService(FicheroRepository pFicheroRepository) {
		this.ficheroRepository = pFicheroRepository;
	}

	public Iterable<Fichero> list() {
		return this.ficheroRepository.findAll();
	}

	public Fichero get(Long id) {
		return this.ficheroRepository.findOne(id);
	}

	// save
	public Fichero save(Fichero fich) {
		return this.ficheroRepository.save(fich);
	}

	public void delete(Long id) {
		this.ficheroRepository.delete(id);
	}

}
