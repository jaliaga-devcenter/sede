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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Licitacion extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Debe introducir la fecha de publicación.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaPub;

	@NotEmpty(message = "Debe introducir una descripción.")
	@Column(nullable = false)
	private String descripcion;

	@Min(1)
	@NotNull(message = "Debe introducir el presupuesto.")
	@Column
	private BigDecimal presupuesto;

	@Column(name = "finPlazo")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Debe introducir la fecha de fin de plazo.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finPlazo;

	@Column(columnDefinition = "INT2")
	private Medio medio;

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

	public Date getFechaPub() {
		return this.fechaPub;
	}

	public void setFechaPub(Date pFechaPub) {
		this.fechaPub = pFechaPub;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public BigDecimal getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(BigDecimal pPresupuesto) {
		this.presupuesto = pPresupuesto;
	}

	public Date getFinPlazo() {
		return this.finPlazo;
	}

	public void setFinPlazo(Date pFinPlazo) {
		this.finPlazo = pFinPlazo;
	}

	public Medio getMedio() {
		return this.medio;
	}

	public void setMedio(Medio pMedio) {
		this.medio = pMedio;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile file) {
		this.fileToUpload = file;
	}
}