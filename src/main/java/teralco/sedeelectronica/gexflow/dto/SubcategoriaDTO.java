package teralco.sedeelectronica.gexflow.dto;

public class SubcategoriaDTO extends AbstractDTO {
	private Integer idSubcategoria;
	private String nombre;
	private String descripcion;

	public SubcategoriaDTO(Integer idSubcategoria, String nombre, String descripcion) {
		this.idSubcategoria = idSubcategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
