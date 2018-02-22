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
public class Documentacion extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Debe introducir una descripción.")
	@Column(nullable = false)
	private String descripcion;

	@Column(columnDefinition = "INT2")
	private Estado estado;

	/* For upload file in form */
	@Transient
	private MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero; // private Long idFichero;

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String _descripcion) {
		this.descripcion = _descripcion;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado _estado) {
		this.estado = _estado;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile _fileToUpload) {
		this.fileToUpload = _fileToUpload;
	}

	public Fichero getFichero() {
		return this.fichero;
	}

	public void setFichero(Fichero _fichero) {
		this.fichero = _fichero;
	}

	@Override
	public String toString() {
		return "Documentacion [descripcion=" + this.descripcion + ", estado=" + this.estado + ", fileToUpload=" + this.fileToUpload
				+ ", fichero=" + this.fichero + "]";
	}
}