package teralco.sedeelectronica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Modelo extends BaseModel {

	private static final long serialVersionUID = 1L;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "modelo_lenguaje", joinColumns = @JoinColumn(name = "modelo_id"))
	private List<ModeloLenguaje> traducciones = new ArrayList<>();

	public List<ModeloLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<ModeloLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile pFileToUpload) {
		this.fileToUpload = pFileToUpload;
	}

	public Fichero getFichero() {
		return this.fichero;
	}

	public void setFichero(Fichero pFichero) {
		this.fichero = pFichero;
	}

}