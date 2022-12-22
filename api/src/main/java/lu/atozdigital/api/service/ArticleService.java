package lu.atozdigital.api.service;

import lu.atozdigital.api.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    //Save Article
    Article saveArticle(Article article);

    //Get all articles
    List<Article> getAllArticles();

    //Get Article by ID
    Article getArticleById(Long id);


}
