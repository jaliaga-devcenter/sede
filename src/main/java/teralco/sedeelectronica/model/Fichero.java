package teralco.sedeelectronica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Fichero extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "INT2")
	@Enumerated(EnumType.ORDINAL)
	private Tipo tipo;

	private String uuid;
	private String nombreOriginal;
	private Double tamanyo;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombreOriginal;
	}

	public void setNombre(String nombre) {
		this.nombreOriginal = nombre;
	}

	public Double getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(Double tamanyo) {
		this.tamanyo = tamanyo;
	}

	@Override
	public String toString() {
		return "Fichero [tipo=" + tipo + ", fichero=" + nombreOriginal + "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}