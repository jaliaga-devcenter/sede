package teralco.sedeelectronica.model;

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
import javax.validation.constraints.NotNull;

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
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@Column(name = "hora")
	@Temporal(TemporalType.TIME)
	@NotNull
	@DateTimeFormat(pattern = "hh:mm")
	private Date hora;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero resultado;

	@Valid
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "apertura_lenguaje", joinColumns = @JoinColumn(name = "apertura_id"))
	private List<AperturaLenguaje> traducciones = new ArrayList<>();

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

	public List<AperturaLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<AperturaLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}

}