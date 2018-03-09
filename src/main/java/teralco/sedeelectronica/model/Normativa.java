package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Normativa extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotEmpty(message = "Debe introducir una norma.")
	private String norma;

	@Column(nullable = false)
	@NotEmpty(message = "Debe introducir una artículo.")
	private String articulo;

	@Column(nullable = true)
	@NotEmpty(message = "Debe introducir un texto.")
	private String texto;

	/* For upload file in form */
	@Transient
	private transient MultipartFile fileToUpload;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fichero", nullable = true)
	private Fichero fichero;

	public String getNorma() {
		return this.norma;
	}

	public void setNorma(String pNorma) {
		this.norma = pNorma;
	}

	public String getArticulo() {
		return this.articulo;
	}

	public void setArticulo(String pArticulo) {
		this.articulo = pArticulo;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String pTexto) {
		this.texto = pTexto;
	}

	public MultipartFile getFileToUpload() {
		return this.fileToUpload;
	}

	public void setFileToUpload(MultipartFile pFileToUpload) {
		this.fileToUpload = pFileToUpload;
	}

	public Fichero getFichero() {
		return this.fichero;
	}

	public void setFichero(Fichero pFichero) {
		this.fichero = pFichero;
	}

}