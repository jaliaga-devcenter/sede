package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NormativaLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = true)
	private String norma;

	@Column(nullable = true)
	private String articulo;

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