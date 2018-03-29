package teralco.sedeelectronica.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Noticia extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = false)
	private String titulo;

	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp fecha;

	@NotEmpty
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

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp pFecha) {
		this.fecha = pFecha;
	}

}
