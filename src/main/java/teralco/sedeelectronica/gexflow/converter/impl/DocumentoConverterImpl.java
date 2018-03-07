package teralco.sedeelectronica.gexflow.converter.impl;

import gexflow.wsdl.DocumentoWSJB;
import teralco.sedeelectronica.gexflow.converter.DocumentoConverter;
import teralco.sedeelectronica.gexflow.dto.DocumentoDTO;

public class DocumentoConverterImpl implements DocumentoConverter {

	@Override
	public DocumentoDTO createFrom(DocumentoWSJB entity) {
		DocumentoDTO documento = new DocumentoDTO(entity.getIdDocumento(), entity.getNombre(),
				entity.getTipoDocumento(), entity.getEnlace());
		return documento;
	}

}
