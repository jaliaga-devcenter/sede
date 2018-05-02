package teralco.sedeelectronica.gexflow.converter.impl;

import org.springframework.stereotype.Component;

import gexflow.wsdl.ContenidoDocumentoWS;
import teralco.sedeelectronica.gexflow.converter.IconoConverter;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;

@Component
public class IconoConverterImpl implements IconoConverter {

	@Override
	public IconoDTO createFrom(ContenidoDocumentoWS entity) {
		return entity == null ? null
				: new IconoDTO(entity.getNombre(), entity.getExtension(), entity.getDocumento());
	}

}
