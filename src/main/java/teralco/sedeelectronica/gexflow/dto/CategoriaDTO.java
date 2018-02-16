package teralco.sedeelectronica.gexflow.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDTO extends AbstractDTO {

	private Integer idCategoria;
	private String nombre;
	private String descripcion;
	private List<SubcategoriaDTO> subcategorias;

	public CategoriaDTO(Integer idCategoria, String nombre, String descripcion) {
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.subcategorias = new ArrayList<>();
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<SubcategoriaDTO> getSubcategorias() {
		return subcategorias;
	}

}
