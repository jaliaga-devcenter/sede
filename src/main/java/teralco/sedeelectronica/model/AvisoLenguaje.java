package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class AvisoLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(nullable = false)
	private String descripcion;

	public AvisoLenguaje() {
	}

	public AvisoLenguaje(String codigo) {
		this.setIdioma(codigo);
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}
}