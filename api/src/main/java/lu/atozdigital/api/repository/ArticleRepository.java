package lu.atozdigital.api.repository;

import lu.atozdigital.api.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article,Long> {


}
