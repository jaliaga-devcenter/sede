package teralco.sedeelectronica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import teralco.sedeelectronica.model.BaseModel;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	public Iterable<T> findAllByOrderByIdAsc();

	public T findById(Long id);

}
