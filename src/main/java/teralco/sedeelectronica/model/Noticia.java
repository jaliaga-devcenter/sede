package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Noticia extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Debe introducir un título.")
	@Column(nullable = false)
	private String titulo;

	@NotEmpty(message = "Debe introducir una noticia.")
	@Column(nullable = false)
	private String descripcion;

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
