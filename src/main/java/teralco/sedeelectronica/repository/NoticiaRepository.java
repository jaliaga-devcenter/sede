package teralco.sedeelectronica.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import teralco.sedeelectronica.model.Noticia;

@Repository
public interface NoticiaRepository extends BaseRepository<Noticia> {
	List<Noticia> findAllByOrderByFechaDesc();
}