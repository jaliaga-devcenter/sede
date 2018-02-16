package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.repository.FicheroRepository;

@Service
public class FicheroService {
	private FicheroRepository ficheroRepository;

	@Autowired
	public FicheroService(FicheroRepository ficheroRepository) {
		this.ficheroRepository = ficheroRepository;
	}

	public Iterable<Fichero> list() {
		return ficheroRepository.findAll();
	}

	public Fichero get(Long id) {
		return ficheroRepository.findOne(id);
	}

	// save
	public Fichero save(Fichero fich) {
		return ficheroRepository.save(fich);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		ficheroRepository.delete(id);
	}
}
