package teralco.sedeelectronica.gexflow.dto;

public class ServicioDTO extends AbstractDTO {

	private Integer idServicio;
	private String denominacion;
	private String descripcion;
	private String documentacion;
	private String tipoTramite;
	private Integer idCategoria;
	private Integer idSubCategoria;

	private String textoPresencial;
	private Boolean canalOnline;
	private String textoOnline;
	private Boolean canalTelefonico;
	private String textoTelefonico;

	private String destinatarios;
	private String formaInicio;
	private String costeYFormadePago;
	private String plazoResolucion;
	private String organosCompetentes;
	private String recursos;
	private String fundamentoLegal;
	private String preguntasFrecuentes;
	private String contactar;

	public ServicioDTO(Integer pIdServicio, String pDenominacion, String pDescripcion, String pTipoTramite,
			String pDocumentacion, String pTextoPresencial, Boolean pCanalOnline, String pTextoOnline,
			Boolean pCanalTelefonico, String pTextoTelefonico, String pDestinatarios, String pFormaInicio,
			String pCosteYFormadePago, String pPlazoResolucion, String pOrganosCompetentes, String pRecursos,
			String pFundamentoLegal, String pPreguntasFrecuentes, String pContactar) {
		this.idServicio = pIdServicio;
		this.denominacion = pDenominacion;
		this.descripcion = pDescripcion;
		this.documentacion = pDocumentacion;
		this.tipoTramite = pTipoTramite;

		this.textoPresencial = pTextoPresencial;
		this.canalOnline = pCanalOnline;
		this.textoOnline = pTextoOnline;
		this.canalTelefonico = pCanalTelefonico;
		this.textoTelefonico = pTextoTelefonico;

		this.destinatarios = pDestinatarios;
		this.formaInicio = pFormaInicio;
		this.costeYFormadePago = pCosteYFormadePago;
		this.plazoResolucion = pPlazoResolucion;
		this.organosCompetentes = pOrganosCompetentes;
		this.recursos = pRecursos;
		this.fundamentoLegal = pFundamentoLegal;
		this.preguntasFrecuentes = pPreguntasFrecuentes;
		this.contactar = pContactar;

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

	public Integer getIdServicio() {
		return this.idServicio;
	}

	public String getTextoPresencial() {
		return this.textoPresencial;
	}

	public Boolean getCanalOnline() {
		return this.canalOnline;
	}

	public String getTextoOnline() {
		return this.textoOnline;
	}

	public Boolean getCanalTelefonico() {
		return this.canalTelefonico;
	}

	public String getTextoTelefonico() {
		return this.textoTelefonico;
	}

	public String getDestinatarios() {
		return this.destinatarios;
	}

	public String getFormaInicio() {
		return this.formaInicio;
	}

	public String getCosteYFormadePago() {
		return this.costeYFormadePago;
	}

	public String getPlazoResolucion() {
		return this.plazoResolucion;
	}

	public String getOrganosCompetentes() {
		return this.organosCompetentes;
	}

	public String getRecursos() {
		return this.recursos;
	}

	public String getFundamentoLegal() {
		return this.fundamentoLegal;
	}

	public String getPreguntasFrecuentes() {
		return this.preguntasFrecuentes;
	}

	public String getContactar() {
		return this.contactar;
	}

}
