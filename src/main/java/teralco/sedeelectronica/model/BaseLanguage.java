package teralco.sedeelectronica.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public class BaseLanguage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1220734997612787212L;
	@NotEmpty
	private String idioma;

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String pIdioma) {
		this.idioma = pIdioma;
	}

}
