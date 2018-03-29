package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class NoticiaLenguaje extends BaseLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = false)
	private String titulo;

	@NotEmpty
	@Column(nullable = false)
	private String descripcion;

	public NoticiaLenguaje() {
	}

	public NoticiaLenguaje(String codigo) {
		this.setIdioma(codigo);
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String pTitulo) {
		this.titulo = pTitulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}
}