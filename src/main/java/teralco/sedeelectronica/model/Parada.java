package teralco.sedeelectronica.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import teralco.sedeelectronica.utils.LanguageUtils;

@Entity
public class Parada extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "parada_lenguaje", joinColumns = @JoinColumn(name = "parada_id"))
	private List<ParadaLenguaje> traducciones = new ArrayList<>();

	private transient Map<String, ParadaLenguaje> traduccionesMap = new HashMap<>();

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

	public String getDescripcion() {
		String language = LanguageUtils.getLanguage();
		return this.traduccionesMap.get(language).getDescripcion();
	}

	@PostLoad
	public void loadTranslationsMap() {
		this.traducciones.forEach(traduccion -> this.traduccionesMap.put(traduccion.getIdioma(), traduccion));
	}

}
