package teralco.sedeelectronica.gexflow.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gexflow.wsdl.CategoriaWS;
import gexflow.wsdl.SubcategoriaWS;
import gexflow.wsdl.SubcategoriasWS;
import teralco.sedeelectronica.gexflow.converter.CategoriaConverter;
import teralco.sedeelectronica.gexflow.converter.SubcategoriaConverter;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;

@Component
public class CategoriaConverterImpl implements CategoriaConverter {

	@Autowired
	private SubcategoriaConverter subcategoriaConverter;

	@Override
	public CategoriaDTO createFrom(CategoriaWS entity) {
		if (entity == null) {
			return null;
		}

		CategoriaDTO categoriaDTO = new CategoriaDTO(entity.getIdCategoria(), entity.getNombre(),
				entity.getDescripcion());

		List<SubcategoriaWS> subcategorias = this.getSubcategorias(entity.getSubcategorias());

		categoriaDTO.getSubcategorias().addAll(subcategoriaConverter.createFromEntities(subcategorias));

		return categoriaDTO;
	}

	private List<SubcategoriaWS> getSubcategorias(SubcategoriasWS subcategoriasWS) {
		if (subcategoriasWS == null || subcategoriasWS.getSubcategoria() == null) {
			return new ArrayList<>(0);
		}

		return subcategoriasWS.getSubcategoria();
	}

}
