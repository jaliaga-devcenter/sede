package teralco.sedeelectronica.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Parada;
import teralco.sedeelectronica.repository.ParadaRepository;

@Service
public class ParadaService {

	private ParadaRepository paradaRepository;

	@Autowired
	public ParadaService(ParadaRepository pParadaRepository) {
		this.paradaRepository = pParadaRepository;
	}

	public Iterable<Parada> list() {
		return this.paradaRepository.findAll();
	}

	public Parada get(Long id) {
		return this.paradaRepository.findOne(id);
	}

	// save
	public Parada save(Parada parada) {
		return this.paradaRepository.save(parada);
	}

	public void delete(Long id) {
		this.paradaRepository.delete(id);
	}

	// Pagination
	public Page<Parada> listAllByPage(Pageable pageable) {
		return this.paradaRepository.findAll(pageable);
	}

	public List<Parada> findAllByFechaOrderByFecha(Date pDate) {
		return this.paradaRepository.findAllByFechaGreaterThanEqualOrderByFecha(pDate);
	}
}
