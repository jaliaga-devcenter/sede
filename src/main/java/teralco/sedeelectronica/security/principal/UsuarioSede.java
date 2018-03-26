package teralco.sedeelectronica.security.principal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioSede {

	public UsuarioSede() {
	}

	private String documento;
	private String nombreCompleto;
	private String ticket;

	public String getDocumento() {
		return this.documento;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public String getTicket() {
		return this.ticket;
	}

	public void setDocumento(String pDocumento) {
		this.documento = pDocumento;
	}

	public void setNombreCompleto(String pNombreCompleto) {
		this.nombreCompleto = pNombreCompleto;
	}

	public void setTicket(String pTicket) {
		this.ticket = pTicket;
	}

}
