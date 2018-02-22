package teralco.sedeelectronica.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Fichero extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "INT2")
	@Enumerated(EnumType.ORDINAL)
	private Tipo tipo;

	@OneToMany(mappedBy = "fichero")
	private Set<Licitacion> licitaciones = new HashSet<Licitacion>();

	private String uuid;
	private String nombreOriginal;
	private Double tamanyo;

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo _tipo) {
		this.tipo = _tipo;
	}

	public Double getTamanyo() {
		return this.tamanyo;
	}

	public void setTamanyo(Double _tamanyo) {
		this.tamanyo = _tamanyo;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String _uuid) {
		this.uuid = _uuid;
	}

	public Set<Licitacion> getLicitaciones() {
		return this.licitaciones;
	}

	public void setLicitaciones(Set<Licitacion> _licitaciones) {
		this.licitaciones = _licitaciones;
	}

	public String getNombreOriginal() {
		return this.nombreOriginal;
	}

	public void setNombreOriginal(String _nombreOriginal) {
		this.nombreOriginal = _nombreOriginal;
	}

	@Override
	public String toString() {
		return "Fichero [tipo=" + this.tipo + ", fichero=" + this.nombreOriginal + "]";
	}
}