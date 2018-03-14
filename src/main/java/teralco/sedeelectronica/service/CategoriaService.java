package teralco.sedeelectronica.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;
import teralco.sedeelectronica.gexflow.client.GexflowClient;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;

@Service
public class CategoriaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);

	@Autowired
	private GexflowClient clienteWS;

	public List<CategoriaDTO> getCategorias(Integer entidad, String language) {
		List<CategoriaDTO> categorias;
		try {
			categorias = this.clienteWS.getCategorias(entidad, language);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}
		return categorias;
	}

	public Map<Integer, IconoDTO> getIconosPorCategoria(Integer entidad, String language,
			List<CategoriaDTO> categorias) {
		return categorias.stream().map(categoria -> {
			try {
				return this.clienteWS.getIconoCategoria(entidad, language, categoria.getIdCategoria());
			} catch (GexflowWSException e) {
				throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
			}
		}).collect(Collectors.toMap(IconoDTO::getIdCategoria, icono -> icono));
	}

	public ServicioDTO getServicio(Integer entidad, String language, Integer idServicio) {
		ServicioDTO servicio;
		try {
			servicio = this.clienteWS.getServicio(entidad, language, idServicio);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}

		return servicio;
	}

	public Map<Integer, List<ServicioDTO>> getServiciosPorSubCategorias(Integer entidad, String language,
			CategoriaDTO categoria) {
		Map<Integer, List<ServicioDTO>> returnList = new HashMap<>();

		categoria.getSubcategorias().forEach(subcategoria -> {
			List<ServicioDTO> servicios = null;
			try {
				servicios = this.clienteWS.getServicios(entidad, language, categoria, subcategoria);
			} catch (GexflowWSException e) {
				LOGGER.error(
						"Error en la invocación al servicio, probablemente no hayan servicios para esa subcategoria.",
						e);
			}
			returnList.put(subcategoria.getIdSubcategoria(), servicios);
		});

		return returnList;
	}

	public Map<Integer, List<ServicioDTO>> getServiciosPorTexto(Integer entidad, String language, String searchText) {
		List<ServicioDTO> servicios = null;
		try {
			servicios = this.clienteWS.buscarServicios(entidad, language, searchText);
		} catch (GexflowWSException e) {
			LOGGER.error("Error en la invocación al servicio, probablemente no hayan servicios para esa subcategoria.",
					e);

		}
		if (servicios != null) {
			return servicios.stream().collect(Collectors.groupingBy(ServicioDTO::getIdSubCategoria));
		}

		return null;

	}

}
