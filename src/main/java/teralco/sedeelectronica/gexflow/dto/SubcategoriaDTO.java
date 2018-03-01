package teralco.sedeelectronica.gexflow.dto;

public class SubcategoriaDTO extends AbstractDTO {
	private Integer idSubcategoria;
	private String nombre;
	private String descripcion;

	public SubcategoriaDTO(Integer pIdSubcategoria, String pNombre, String pDescripcion) {
		this.idSubcategoria = pIdSubcategoria;
		this.nombre = pNombre;
		this.descripcion = pDescripcion;
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

	@Override
	public String toString() {
		return "SubcategoriaDTO [idSubcategoria=" + this.idSubcategoria + ", nombre=" + this.nombre + ", descripcion="
				+ this.descripcion + "]";
	}

}
