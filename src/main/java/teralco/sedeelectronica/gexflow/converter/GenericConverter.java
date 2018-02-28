package teralco.sedeelectronica.gexflow.converter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import teralco.sedeelectronica.gexflow.dto.AbstractDTO;

public interface GenericConverter<E, D extends AbstractDTO> {

	D createFrom(E entity);

	default List<D> createFromEntities(final Collection entities) {
		Function<E, D> createFrom = this::createFrom;
		return (List<D>) entities.stream().map(createFrom).collect(Collectors.toList());
	}

}
