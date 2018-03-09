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
public class Apertura extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Debe introducir la fecha de apertura.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@NotEmpty(message = "Debe introducir una denominación.")
	@Column(nullable = false)
	private String denominacion;

	@Column(name = "hora")
	@Temporal(TemporalType.TIME)
	@NotNull(message = "Debe introducir la hora de apertura.")
	@DateTimeFormat(pattern = "hh:mm")
	private Date hora;

	@Column(nullable = true)
	private String plica;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero resultado;

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date pFecha) {
		this.fecha = pFecha;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String pDenominacion) {
		this.denominacion = pDenominacion;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date pHora) {
		this.hora = pHora;
	}

	public String getPlica() {
		return this.plica;
	}

	public void setPlica(String pPlica) {
		this.plica = pPlica;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile pFileToUpload) {
		this.fileToUpload = pFileToUpload;
	}

	public Fichero getResultado() {
		return this.resultado;
	}

	public void setResultado(Fichero pResultado) {
		this.resultado = pResultado;
	}
}