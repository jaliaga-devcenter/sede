package teralco.sedeelectronica.gexflow.converter.impl;

import org.springframework.stereotype.Component;

import gexflow.wsdl.ServicioWSJB;
import teralco.sedeelectronica.gexflow.converter.ServicioConverter;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;

@Component
public class ServicioConverterImpl implements ServicioConverter {

	@Override
	public ServicioDTO createFrom(ServicioWSJB entity) {

		ServicioDTO servicio = new ServicioDTO(entity.getDenominacion(), entity.getDescripcion(),
				entity.getTipoTramite(), entity.getDocumentacion(), entity.isCanalInternetHabilitado());
		return servicio;
	}

}
