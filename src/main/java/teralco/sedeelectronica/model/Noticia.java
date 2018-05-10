package teralco.sedeelectronica.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.Valid;

@Entity
public class Noticia extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Valid
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "noticia_lenguaje", joinColumns = @JoinColumn(name = "noticia_id"))
	private List<NoticiaLenguaje> traducciones = new ArrayList<>();

	public List<NoticiaLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<NoticiaLenguaje> ptraducciones) {
		this.traducciones = ptraducciones;
	}

	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp fecha;

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp pFecha) {
		this.fecha = pFecha;
	}

}
