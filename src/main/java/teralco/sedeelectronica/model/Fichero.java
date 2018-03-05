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
		return this.tipo;
	}

	public void setTipo(Tipo pTipo) {
		this.tipo = pTipo;
	}

	public Double getTamanyo() {
		return this.tamanyo;
	}

	public void setTamanyo(Double pTamanyo) {
		this.tamanyo = pTamanyo;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String pUuid) {
		this.uuid = pUuid;
	}

	public String getNombreOriginal() {
		return this.nombreOriginal;
	}

	public void setNombreOriginal(String pNombreOriginal) {
		this.nombreOriginal = pNombreOriginal;
	}

	@Override
	public String toString() {
		return "Fichero [tipo=" + this.tipo + ", fichero=" + this.nombreOriginal + "]";
	}
}