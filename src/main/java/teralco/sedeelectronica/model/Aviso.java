package teralco.sedeelectronica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Aviso extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Debe introducir una descripción.")
	@Column(nullable = false)
	private String descripcion;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Debe introducir la fecha.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

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

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date _fecha) {
		this.fecha = _fecha;
	}

	@Override
	public String toString() {
		return "Documentacion [descripcion=" + this.descripcion + ", fileToUpload=" + this.fileToUpload + ", fichero=" + this.fichero
				+ "]";
	}
}