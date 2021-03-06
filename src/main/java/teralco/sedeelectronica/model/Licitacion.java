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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Licitacion extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaPub;

	@Valid
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "licitacion_lenguaje", joinColumns = @JoinColumn(name = "licitacion_id"))
	private List<LicitacionLenguaje> traducciones = new ArrayList<>();

	@Min(1)
	@NotNull
	@Column
	private BigDecimal presupuesto;

	@Column(name = "finPlazo")
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finPlazo;

	@NotNull
	private Short medio;

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

	public Short getMedio() {
		return this.medio;
	}

	public void setMedio(Short pMedio) {
		this.medio = pMedio;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile file) {
		this.fileToUpload = file;
	}

	public List<LicitacionLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<LicitacionLenguaje> pLanguage) {
		this.traducciones = pLanguage;
	}
}