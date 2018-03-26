package teralco.sedeelectronica.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
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
		return getServiciosPorSubCategorias(entidad, language, categoria, Optional.empty());
	}

	public Map<Integer, List<ServicioDTO>> getServiciosPorSubCategorias(Integer entidad, String language,
			CategoriaDTO categoria, Optional<Predicate<ServicioDTO>> filter) {
		Map<Integer, List<ServicioDTO>> returnList = new HashMap<>();

		categoria.getSubcategorias().forEach(subcategoria -> {
			List<ServicioDTO> serviciosSinFiltrar = null;
			List<ServicioDTO> serviciosFiltrados = new ArrayList<>();
			try {
				serviciosSinFiltrar = this.clienteWS.getServicios(entidad, language, categoria, subcategoria);
				serviciosFiltrados = serviciosSinFiltrar;
				if (filter.isPresent()) {
					serviciosFiltrados = serviciosSinFiltrar.stream().filter(filter.get()).collect(Collectors.toList());
				}
			} catch (GexflowWSException e) {
				LOGGER.error(
						"Error en la invocación al servicio, probablemente no hayan servicios para esa subcategoria.",
						e);
			}

			if (!serviciosFiltrados.isEmpty()) {
				returnList.put(subcategoria.getIdSubcategoria(), serviciosFiltrados);
			}
		});

		return returnList;
	}

}
