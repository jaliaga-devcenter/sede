package teralco.sedeelectronica.gexflow.dto;

import java.util.List;

public class CategoriaDTO extends AbstractDTO {

	private Integer idCategoria;
	private String nombre;
	private String descripcion;
	private List<SubcategoriaDTO> subcategorias;

	public CategoriaDTO(Integer _idCategoria, String _nombre, String _descripcion) {
		this.idCategoria = _idCategoria;
		this.nombre = _nombre;
		this.descripcion = _descripcion;
		// this.subcategorias = new ArrayList<>();
	}

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public List<SubcategoriaDTO> getSubcategorias() {
		return this.subcategorias;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CategoriaDTO))
			return false;
		CategoriaDTO other = (CategoriaDTO) o;
		return other.idCategoria.equals(this.idCategoria);
	}

	public void setSubcategorias(List<SubcategoriaDTO> pSubcategorias) {
		// TODO Auto-generated method stub
		this.subcategorias = pSubcategorias;
	}
}
