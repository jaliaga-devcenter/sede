package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Normativa;
import teralco.sedeelectronica.repository.NormativaRepository;

@Service
public class NormativaService {

	private NormativaRepository normativaRepository;

	@Autowired
	public NormativaService(NormativaRepository pNormativaRepository) {
		this.normativaRepository = pNormativaRepository;
	}

	public Iterable<Normativa> list() {
		return this.normativaRepository.findAll();
	}

	public Normativa get(Long id) {
		return this.normativaRepository.findOne(id);
	}

	// save
	public Normativa save(Normativa pNormativa) {
		return this.normativaRepository.save(pNormativa);
	}

	public void delete(Long id) {
		this.normativaRepository.delete(id);
	}

	// Pagination
	public Page<Normativa> listAllByPage(Pageable pageable) {
		return this.normativaRepository.findAll(pageable);
	}
}
