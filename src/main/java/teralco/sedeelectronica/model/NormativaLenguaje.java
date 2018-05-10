package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class NormativaLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = true)
	private String norma;

	@NotEmpty
	@Column(nullable = true)
	private String articulo;

	@NotEmpty
	@Column(nullable = true)
	private String texto;

	public NormativaLenguaje() {
	}

	public NormativaLenguaje(String codigo) {
		this.setIdioma(codigo);
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