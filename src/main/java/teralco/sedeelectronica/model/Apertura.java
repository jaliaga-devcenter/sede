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
	private MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero resultado; // private Long idFichero;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getPlica() {
		return plica;
	}

	public void setPlica(String plica) {
		this.plica = plica;
	}

	public MultipartFile getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(MultipartFile fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public Fichero getResultado() {
		return resultado;
	}

	public void setResultado(Fichero resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "Apertura [fecha=" + fecha + ", denominacion=" + denominacion + ", hora=" + hora + ", plica=" + plica
				+ ", fileToUpload=" + fileToUpload + ", resultado=" + resultado + "]";
	}
}