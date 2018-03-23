package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Lenguaje extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(unique = true, nullable = false)
	private String codigo;

	@NotEmpty
	@Column(unique = true, nullable = false)
	private String idioma;

	public Lenguaje() {

	}

	public Lenguaje(String pCodigo, String pIdioma) {
		this.codigo = pCodigo;
		this.idioma = pIdioma;
	}

	public Lenguaje(String pCodigo) {
		this.codigo = pCodigo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String pIdioma) {
		this.idioma = pIdioma;
	}

	public void setCodigo(String pCodigo) {
		this.codigo = pCodigo;
	}

}
