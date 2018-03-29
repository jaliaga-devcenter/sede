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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Anuncio extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "fecha_de")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaDe;

	@Column(name = "fecha_hasta")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaHasta;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "anuncio_lenguaje", joinColumns = @JoinColumn(name = "anuncio_id"))
	private List<AnuncioLenguaje> traducciones = new ArrayList<>();

	public List<AnuncioLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<AnuncioLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}

	public Fichero getFichero() {
		return this.fichero;
	}

	public void setFichero(Fichero pFichero) {
		this.fichero = pFichero;
	}

	public Date getFechaDe() {
		return this.fechaDe;
	}

	public void setFechaDe(Date pFecha) {
		this.fechaDe = pFecha;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date pFecha) {
		this.fechaHasta = pFecha;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile pFileToUpload) {
		this.fileToUpload = pFileToUpload;
	}
}
