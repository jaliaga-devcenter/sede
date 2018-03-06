package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Parada extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Debe introducir la descripción de la parada.")
	@Column(nullable = false)
	private String descripcion;

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

}
