package teralco.sedeelectronica.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Contacto {
	@NotEmpty(message = "Debe escribir un nombre.")
	private String nombre;
	@NotEmpty(message = "Debe escribir un email.")
	private String email;
	@NotEmpty(message = "Debe escribir un comentario.")
	private String comentario;

	public String getNombre() {

		return this.nombre;
	}

	public void setNombre(String _nombre) {
		this.nombre = _nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String _email) {
		this.email = _email;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String _comentario) {
		this.comentario = _comentario;
	}

}
