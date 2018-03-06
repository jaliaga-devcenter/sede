package teralco.sedeelectronica.gexflow.dto;

public class ServicioDTO extends AbstractDTO {

	private String denominacion;
	private String descripcion;
	private String documentacion;
	private String tipoTramite;
	private Integer idCategoria;

	public ServicioDTO(String pDenominacion, String pDescripcion, String pTipoTramite, String pDocumentacion) {
		this.denominacion = pDenominacion;
		this.descripcion = pDescripcion;
		this.documentacion = pDocumentacion;
		this.tipoTramite = pTipoTramite;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String pDenominacion) {
		this.denominacion = pDenominacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDocumentacion() {
		return this.documentacion;
	}

	public void setDocumentacion(String pDocumentacion) {
		this.documentacion = pDocumentacion;
	}

	public String getTipoTramite() {
		return this.tipoTramite;
	}

	public void setTipoTramite(String pTipoTramite) {
		this.tipoTramite = pTipoTramite;
	}

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer pIdCategoria) {
		this.idCategoria = pIdCategoria;
	}

}
