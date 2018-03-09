package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Anuncio;
import teralco.sedeelectronica.repository.AnuncioRepository;

@Service
public class AnuncioService {

	private AnuncioRepository anuncioRepository;

	@Autowired
	public AnuncioService(AnuncioRepository pAnuncioRepository) {
		this.anuncioRepository = pAnuncioRepository;
	}

	public Iterable<Anuncio> list() {
		return this.anuncioRepository.findAll();
	}

	public Anuncio get(Long id) {
		return this.anuncioRepository.findOne(id);
	}

	// save
	public Anuncio save(Anuncio noticia) {
		return this.anuncioRepository.save(noticia);
	}

	public void delete(Long id) {
		this.anuncioRepository.delete(id);
	}

	// Pagination
	public Page<Anuncio> listAllByPage(Pageable pageable) {
		return this.anuncioRepository.findAll(pageable);
	}
}
