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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Anuncio extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Debe introducir un título.")
	@Column(nullable = false)
	private String titulo;

	@Column(name = "fecha_de")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaDe;

	@Column(name = "fecha_hasta")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaHasta;

	@NotEmpty(message = "Debe introducir una noticia.")
	@Column(nullable = false)
	private String descripcion;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero;

	public Fichero getFichero() {
		return this.fichero;
	}

	public void setFichero(Fichero pFichero) {
		this.fichero = pFichero;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String pTitulo) {
		this.titulo = pTitulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public Date getFechaDe() {
		return this.fechaDe;
	}

	public void setFechaDe(Date pFecha) {
		this.fechaDe = pFecha;
	}

	public Date getFecha() {
		return this.fechaHasta;
	}

	public void setFecha(Date pFecha) {
		this.fechaHasta = pFecha;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}
}
