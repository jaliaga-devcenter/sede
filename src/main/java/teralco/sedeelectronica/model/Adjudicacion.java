package teralco.sedeelectronica.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Adjudicacion extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotEmpty(message = "Debe introducir una denominación.")
	private String denominacion;

	/* PARTE DE PLICA */
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@Column(name = "hora")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm")
	private Date hora;

	@Column(nullable = true)
	private String plica;

	/* fin plica */

	@Column(name = "fecha_formalizacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFormalizacion;

	@Column(name = "empresa_adjudicacion")
	private String empresaAdjudicacion;

	@Min(1)
	@Column
	private BigDecimal presupuesto;

	@Column(name = "fecha_adjudicacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAdjudicacion;

	/* For upload file in form */
	@Transient
	private MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero resultado; // private Long idFichero;

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String _denominacion) {
		this.denominacion = _denominacion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date _fecha) {
		this.fecha = _fecha;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date _hora) {
		this.hora = _hora;
	}

	public String getPlica() {
		return this.plica;
	}

	public void setPlica(String _plica) {
		this.plica = _plica;
	}

	public Date getFechaFormalizacion() {
		return this.fechaFormalizacion;
	}

	public void setFechaFormalizacion(Date _fechaFormalizacion) {
		this.fechaFormalizacion = _fechaFormalizacion;
	}

	public String getEmpresaAdjudicacion() {
		return this.empresaAdjudicacion;
	}

	public void setEmpresaAdjudicacion(String adjudicacion) {
		this.empresaAdjudicacion = adjudicacion;
	}

	public BigDecimal getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(BigDecimal _presupuesto) {
		this.presupuesto = _presupuesto;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile _fileToUpload) {
		this.fileToUpload = _fileToUpload;
	}

	public Fichero getResultado() {
		return this.resultado;
	}

	public void setResultado(Fichero _resultado) {
		this.resultado = _resultado;
	}

	public Date getFechaAdjudicacion() {
		return this.fechaAdjudicacion;
	}

	public void setFechaAdjudicacion(Date _fechaAdjudicacion) {
		this.fechaAdjudicacion = _fechaAdjudicacion;
	}

	@Override
	public String toString() {
		return "Adjudicacion [denominacion=" + this.denominacion + ", fecha=" + this.fecha + ", hora=" + this.hora + ", plica=" + this.plica
				+ ", fechaFormalizacion=" + this.fechaFormalizacion + ", adjudicacion=" + this.empresaAdjudicacion
				+ ", presupuesto=" + this.presupuesto + ", fileToUpload=" + this.fileToUpload + ", resultado=" + this.resultado + "]";
	}
}