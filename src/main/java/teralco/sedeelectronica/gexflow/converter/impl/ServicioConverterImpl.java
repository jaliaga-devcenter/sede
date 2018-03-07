package teralco.sedeelectronica.gexflow.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gexflow.wsdl.ServicioWSJB;
import teralco.sedeelectronica.gexflow.converter.DocumentoConverter;
import teralco.sedeelectronica.gexflow.converter.ServicioConverter;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;

@Component
public class ServicioConverterImpl implements ServicioConverter {

	@Autowired
	private DocumentoConverter documentoConverter;

	@Override
	public ServicioDTO createFrom(ServicioWSJB entity) {

		ServicioDTO servicio = new ServicioDTO(entity.getIdentificadorServicio(), entity.getDenominacion(),
				entity.getDescripcion(), entity.getTipoTramite(), entity.getDocumentacion(),
				entity.getCanalPresencialTexto(), entity.isCanalInternetHabilitado(), entity.getCanalInternetTexto(),
				entity.isCanalTelefonicoHabilitado(), entity.getCanalTelefonico(), entity.getDestinatarios(),
				entity.getFormaInicio(), entity.getCosteFormaPago(), entity.getPlazoMaximoResolucion(),
				entity.getOrganosCompetentes(), entity.getRecursos(), entity.getFundamentoLegal(),
				entity.getPreguntasFrecuentes(), entity.getContactar());

		servicio.getDocumentos().addAll(
				this.documentoConverter.createFromEntities(entity.getDocumentacionRelacionada().getDocumento()));
		return servicio;
	}

}
