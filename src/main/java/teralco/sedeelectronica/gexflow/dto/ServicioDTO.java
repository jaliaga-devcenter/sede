package teralco.sedeelectronica.gexflow.dto;

public class ServicioDTO extends AbstractDTO {

	private String denominacion;
	private String descripcion;
	private String fechaInicio;
	private String fechaFin;

	public ServicioDTO(String pDenominacion, String pDescripcion, String pFechaInicio, String pFechaFin) {
		this.denominacion = pDenominacion;
		this.descripcion = pDescripcion;
		this.fechaInicio = pFechaInicio;
		this.fechaFin = pFechaFin;
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

	public String getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(String pFechaInicio) {
		this.fechaInicio = pFechaInicio;
	}

	public String getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(String pFechaFin) {
		this.fechaFin = pFechaFin;
	}

}
