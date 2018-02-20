package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Tipo;
import teralco.sedeelectronica.repository.FicheroRepository;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;

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

	public Fichero guardarFichero(MultipartFile fichero) {
		Fichero file = null;
		if (fichero.getSize() > 0) {
			String uuid = "";
			try {
				uuid = FicheroUtils.guardarFichero(fichero);
				/* save file in model */
				file = new Fichero();
				file.setNombreOriginal(fichero.getOriginalFilename());
				file.setUuid(uuid);
				file.setTipo(Tipo.pdf);
				file.setTamanyo((double) fichero.getSize());
				file = ficheroRepository.save(file);
				file.setLink(EncryptUtils.encrypt(file.getId().toString()));
				file = ficheroRepository.save(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}
}
