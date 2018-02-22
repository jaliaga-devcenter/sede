package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.repository.ModeloRepository;

@Service
public class ModeloService {

	private ModeloRepository modeloRepository;

	@Autowired
	public ModeloService(ModeloRepository modeloRepository) {
		this.modeloRepository = modeloRepository;
	}

	public Iterable<Modelo> list() {
		return modeloRepository.findAll();
	}

	public Modelo get(Long id) {
		return modeloRepository.findOne(id);
	}

	// save
	public Modelo save(Modelo modelo) {
		return modeloRepository.save(modelo);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		modeloRepository.delete(id);
	}
}
