package teralco.sedeelectronica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Documentacion extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "INT2")
	private Short estado;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "documentacion_lenguaje", joinColumns = @JoinColumn(name = "documentacion_id"))
	private List<DocumentacionLenguaje> traducciones = new ArrayList<>();

	public List<DocumentacionLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<DocumentacionLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}

	public Short getEstado() {
		return this.estado;
	}

	public void setEstado(Short pEstado) {
		this.estado = pEstado;
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