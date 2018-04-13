package teralco.sedeelectronica.gexflow.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDTO extends AbstractDTO {

	private Integer idCategoria;
	private String nombre;
	private String descripcion;
	private List<SubcategoriaDTO> subcategorias;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Integer pIdCategoria, String pNombre, String pDescripcion) {
		this.idCategoria = pIdCategoria;
		this.nombre = pNombre;
		this.descripcion = pDescripcion;
		this.subcategorias = new ArrayList<>();
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

	public void setSubcategorias(List<SubcategoriaDTO> pSubcategorias) {
		this.subcategorias = pSubcategorias;
	}
}
