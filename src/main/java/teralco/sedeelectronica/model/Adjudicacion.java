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
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Date getFechaFormalizacion() {
		return fechaFormalizacion;
	}

	public void setFechaFormalizacion(Date fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}

	public String getEmpresaAdjudicacion() {
		return empresaAdjudicacion;
	}

	public void setEmpresaAdjudicacion(String adjudicacion) {
		this.empresaAdjudicacion = adjudicacion;
	}

	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	public void setFechaAdjudicacion(Date fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	@Override
	public String toString() {
		return "Adjudicacion [denominacion=" + denominacion + ", fecha=" + fecha + ", hora=" + hora + ", plica=" + plica
				+ ", fechaFormalizacion=" + fechaFormalizacion + ", adjudicacion=" + empresaAdjudicacion
				+ ", presupuesto=" + presupuesto + ", fileToUpload=" + fileToUpload + ", resultado=" + resultado + "]";
	}
}