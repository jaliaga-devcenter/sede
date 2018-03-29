package teralco.sedeelectronica.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import teralco.sedeelectronica.model.Anuncio;

@Repository
public interface AnuncioRepository extends BaseRepository<Anuncio> {
	List<Anuncio> findAllByOrderByFechaDeDesc();
}