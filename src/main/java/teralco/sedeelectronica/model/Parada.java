package teralco.sedeelectronica.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Parada extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "noticia_lenguaje", joinColumns = @JoinColumn(name = "noticia_id"))
	private List<ParadaLenguaje> traducciones = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date pFecha) {
		this.fecha = pFecha;
	}

	public List<ParadaLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<ParadaLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}

}
