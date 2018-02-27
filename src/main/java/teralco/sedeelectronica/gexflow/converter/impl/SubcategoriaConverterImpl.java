package teralco.sedeelectronica.gexflow.converter.impl;

import org.springframework.stereotype.Component;

import gexflow.wsdl.SubcategoriaWS;
import teralco.sedeelectronica.gexflow.converter.SubcategoriaConverter;
import teralco.sedeelectronica.gexflow.dto.SubcategoriaDTO;

@Component
public class SubcategoriaConverterImpl implements SubcategoriaConverter {

	@Override
	public SubcategoriaDTO createFrom(SubcategoriaWS entity) {
		if (entity == null) {
			return null;
		}
		return new SubcategoriaDTO(entity.getIdSubcategoria(), entity.getNombre(), entity.getDescripcion());
	}

}
