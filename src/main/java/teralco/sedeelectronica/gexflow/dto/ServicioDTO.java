package teralco.sedeelectronica.gexflow.dto;

public class ServicioDTO extends AbstractDTO {

	private Integer idServicio;
	private String denominacion;
	private String descripcion;
	private String documentacion;
	private String tipoTramite;
	private Boolean tramitacionOnline;
	private Integer idCategoria;
	private Integer idSubCategoria;

	public ServicioDTO(Integer pIdServicio, String pDenominacion, String pDescripcion, String pTipoTramite,
			String pDocumentacion, Boolean pTramitacionOnline) {
		this.idServicio = pIdServicio;
		this.denominacion = pDenominacion;
		this.descripcion = pDescripcion;
		this.documentacion = pDocumentacion;
		this.tipoTramite = pTipoTramite;
		this.tramitacionOnline = pTramitacionOnline;
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

	public ServicioDTO setIdCategoria(Integer pIdCategoria) {
		this.idCategoria = pIdCategoria;
		return this;
	}

	public Integer getIdSubCategoria() {
		return this.idSubCategoria;
	}

	public ServicioDTO setIdSubCategoria(Integer pIdSubCategoria) {
		this.idSubCategoria = pIdSubCategoria;
		return this;
	}

	public Boolean getTramitacionOnline() {
		return this.tramitacionOnline;
	}

	public Integer getIdServicio() {
		return this.idServicio;
	}

}
