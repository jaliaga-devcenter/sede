package teralco.sedeelectronica.gexflow.dto;

public class SubcategoriaDTO extends AbstractDTO {
	private Integer idSubcategoria;
	private String nombre;
	private String descripcion;

	public SubcategoriaDTO(Integer _idSubcategoria, String _nombre, String _descripcion) {
		this.idSubcategoria = _idSubcategoria;
		this.nombre = _nombre;
		this.descripcion = _descripcion;
	}

	public Integer getIdSubcategoria() {
		return this.idSubcategoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

}
