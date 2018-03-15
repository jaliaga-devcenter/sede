package teralco.sedeelectronica.auth.dto;

public class DiaFestivoDTO {

	private String dia;
	private String descripcion;
	private String tipo;

	public DiaFestivoDTO(String pDia, String pDescripcion, String pTipo) {
		this.dia = pDia;
		this.descripcion = pDescripcion;
		this.tipo = pTipo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getDia() {
		return this.dia;
	}

	public String getTipo() {
		return this.tipo;
	}
}
