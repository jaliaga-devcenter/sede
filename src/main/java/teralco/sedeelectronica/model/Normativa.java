package teralco.sedeelectronica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Normativa extends BaseModel {

	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "normativa_lenguaje", joinColumns = @JoinColumn(name = "normativa_id"))
	private List<NormativaLenguaje> traducciones = new ArrayList<>();

	public List<NormativaLenguaje> getTraducciones() {
		return this.traducciones;
	}

	public void setTraducciones(List<NormativaLenguaje> pTraducciones) {
		this.traducciones = pTraducciones;
	}

	@Column(nullable = true, name = "url_norma")
	@NotEmpty
	private String url;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String pUrl) {
		this.url = pUrl;
	}

}