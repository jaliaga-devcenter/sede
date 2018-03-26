package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Modelo extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = false)
	private String descripcion;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero;

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
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