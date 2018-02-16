package teralco.sedeelectronica.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	/**
	 * 
	 */
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

	@Transient
	private transient boolean ivaInc;

	@Column(name = "finPlazo")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Debe introducir la fecha de fin de plazo.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finPlazo;

	@Column(columnDefinition = "INT2")
	private Medio medio;

	/* For upload file in form */
	@Transient
	private MultipartFile fichero;

	private Long idFichero;

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

	public boolean isIvaInc() {
		return ivaInc;
	}

	public void setIvaInc(boolean ivaInc) {
		this.ivaInc = ivaInc;
	}

	public Date getFinPlazo() {
		return finPlazo;
	}

	public void setFinPlazo(Date finPlazo) {
		this.finPlazo = finPlazo;
	}

	public Medio getMedio() {
		return medio;
	}

	public void setMedio(Medio medio) {
		this.medio = medio;
	}

	public MultipartFile getFichero() {
		return fichero;
	}

	public void setFichero(MultipartFile fichero) {
		this.fichero = fichero;
	}

	public Long getIdFichero() {
		return idFichero;
	}

	public void setIdFichero(Long id) {
		this.idFichero = id;
	}

	@Override
	public String toString() {
		return "Licitacion [ fechaPub=" + fechaPub + ", descripcion=" + descripcion + ", presupuesto=" + presupuesto
				+ ", ivaInc=" + ivaInc + ", finPlazo=" + finPlazo + ", medio=" + medio + "]";
	}
}