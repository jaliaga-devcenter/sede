package teralco.sedeelectronica.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Contacto {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String email;
	@NotEmpty
	private String comentario;

	public String getNombre() {

		return this.nombre;
	}

	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String pEmail) {
		this.email = pEmail;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String pComentario) {
		this.comentario = pComentario;
	}

}
