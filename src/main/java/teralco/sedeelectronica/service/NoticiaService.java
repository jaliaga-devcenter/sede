package teralco.sedeelectronica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Noticia;
import teralco.sedeelectronica.repository.NoticiaRepository;

@Service
public class NoticiaService {

	private NoticiaRepository noticiaRepository;

	@Autowired
	public NoticiaService(NoticiaRepository pNoticiaRepository) {
		this.noticiaRepository = pNoticiaRepository;
	}

	public Iterable<Noticia> list() {
		return this.noticiaRepository.findAll();
	}

	public Noticia get(Long id) {
		return this.noticiaRepository.findOne(id);
	}

	// save
	public Noticia save(Noticia noticia) {
		return this.noticiaRepository.save(noticia);
	}

	public void delete(Long id) {
		this.noticiaRepository.delete(id);
	}

	// Pagination
	public Page<Noticia> listAllByPage(Pageable pageable) {
		return this.noticiaRepository.findAll(pageable);
	}

	// List order by
	public List<Noticia> listAll() {
		return this.noticiaRepository.findAllByOrderByFechaDesc();
	}
}
