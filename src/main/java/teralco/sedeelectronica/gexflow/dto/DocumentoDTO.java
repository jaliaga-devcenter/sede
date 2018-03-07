package teralco.sedeelectronica.gexflow.dto;

public class DocumentoDTO extends AbstractDTO {
	protected Integer idDocumento;
	protected String nombre;
	protected String tipoDocumento;
	protected String enlace;

	public DocumentoDTO(Integer pIdDocumento, String pNombre, String pTipoDocumento, String pEnlace) {
		this.idDocumento = pIdDocumento;
		this.nombre = pNombre;
		this.tipoDocumento = pTipoDocumento;
		this.enlace = pEnlace;
	}

	public Integer getIdDocumento() {
		return this.idDocumento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public String getEnlace() {
		return this.enlace;
	}

}
