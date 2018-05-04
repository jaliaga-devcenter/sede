package teralco.sedeelectronica.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Adjudicacion extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* PARTE DE PLICA */
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@Column(name = "hora")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm")
	private Date hora;

	@Column(name = "fecha_formalizacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFormalizacion;

	@Min(1)
	@Column
	private BigDecimal presupuesto;

	@Column(name = "fecha_adjudicacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAdjudicacion;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero resultado;

	@Valid
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "adjudicacion_lenguaje", joinColumns = @JoinColumn(name = "adjudicacion_id"))
	private List<AdjudicacionLenguaje> traducciones = new ArrayList<>();

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date pFecha) {
		this.fecha = pFecha;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date pHora) {
		this.hora = pHora;
	}

	public Date getFechaFormalizacion() {
		return this.fechaFormalizacion;
	}

	public void setFechaFormalizacion(Date pFechaFormalizacion) {
		this.fechaFormalizacion = pFechaFormalizacion;
	}

	public BigDecimal getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(BigDecimal pPresupuesto) {
		this.presupuesto = pPresupuesto;
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

	public Date getFechaAdjudicacion() {
		return this.fechaAdjudicacion;
	}

	public void setFechaAdjudicacion(Date pFechaAdjudicacion) {
		this.fechaAdjudicacion = pFechaAdjudicacion;
	}

	public List<AdjudicacionLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<AdjudicacionLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}
}