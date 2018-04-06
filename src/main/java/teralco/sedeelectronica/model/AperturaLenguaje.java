package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class AperturaLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(nullable = false)
	private String denominacion;

	@Column(nullable = true)
	private String plica;

	public AperturaLenguaje(String codigo) {
		this.setIdioma(codigo);
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String pDenominacion) {
		this.denominacion = pDenominacion;
	}

	public String getPlica() {
		return this.plica;
	}

	public void setPlica(String pPlica) {
		this.plica = pPlica;
	}
}