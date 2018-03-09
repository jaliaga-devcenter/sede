package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

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

	@Column(nullable = true, name = "url_norma")
	@NotEmpty(message = "Debe introducir una url.")
	private String url;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String pUrl) {
		this.url = pUrl;
	}

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

}