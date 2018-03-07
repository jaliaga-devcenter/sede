package teralco.sedeelectronica.gexflow.converter.impl;

import org.springframework.stereotype.Component;

import gexflow.wsdl.DocumentoWSJB;
import teralco.sedeelectronica.gexflow.converter.DocumentoConverter;
import teralco.sedeelectronica.gexflow.dto.DocumentoDTO;

@Component
public class DocumentoConverterImpl implements DocumentoConverter {

	@Override
	public DocumentoDTO createFrom(DocumentoWSJB entity) {
		DocumentoDTO documento = new DocumentoDTO(entity.getIdDocumento(), entity.getNombre(),
				entity.getTipoDocumento(), entity.getEnlace());
		return documento;
	}

}
