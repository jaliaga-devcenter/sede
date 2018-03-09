package teralco.sedeelectronica.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import teralco.sedeelectronica.model.Parada;

@Repository
public interface ParadaRepository extends BaseRepository<Parada> {
	List<Parada> findAllByFechaGreaterThanEqualOrderByFecha(Date pDate);

}
