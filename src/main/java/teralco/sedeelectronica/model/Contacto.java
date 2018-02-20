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
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
