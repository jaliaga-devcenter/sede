package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.repository.ModeloRepository;

@Service
public class ModeloService {

	private ModeloRepository modeloRepository;

	@Autowired

	public ModeloService(ModeloRepository pModeloRepository) {
		this.modeloRepository = pModeloRepository;
	}

	public Iterable<Modelo> list() {
		return this.modeloRepository.findAll();
	}

	public Modelo get(Long id) {
		return this.modeloRepository.findOne(id);
	}

	// save
	public Modelo save(Modelo modelo) {
		return this.modeloRepository.save(modelo);
	}

	public void delete(Long id) {
		this.modeloRepository.delete(id);
	}

	// Pagination
	public Page<Modelo> listAllByPage(Pageable pageable) {
		return this.modeloRepository.findAll(pageable);
	}
}
