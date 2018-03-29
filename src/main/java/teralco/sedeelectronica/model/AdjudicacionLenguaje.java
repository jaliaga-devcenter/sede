package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class AdjudicacionLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	@NotEmpty
	private String denominacion;

	@Column(nullable = true)
	private String plica;

	@Column(name = "empresa_adjudicacion")
	private String empresaAdjudicacion;

	public AdjudicacionLenguaje() {
	}

	public AdjudicacionLenguaje(String codigo) {
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

	public String getEmpresaAdjudicacion() {
		return this.empresaAdjudicacion;
	}

	public void setEmpresaAdjudicacion(String adjudicacion) {
		this.empresaAdjudicacion = adjudicacion;
	}
}