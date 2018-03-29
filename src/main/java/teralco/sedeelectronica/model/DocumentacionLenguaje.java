package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DocumentacionLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private String descripcion;

	public DocumentacionLenguaje() {
	}

	public DocumentacionLenguaje(String codigo) {
		this.setIdioma(codigo);
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	@Override
	public String toString() {
		return "LicitacionLenguaje [descripcion=" + this.descripcion + ", getIdioma()=" + getIdioma() + "]";
	}

}