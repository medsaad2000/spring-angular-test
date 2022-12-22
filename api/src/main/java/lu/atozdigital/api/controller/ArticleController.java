package lu.atozdigital.api.controller;


import lu.atozdigital.api.entity.Article;
import lu.atozdigital.api.service.ArticleService;

import lu.atozdigital.api.util.ArticleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArticleController {
    //public static final String DIRECTORY = System.getProperty("user.home");
    public static final String DIRECTORY = "src/main/resources/static/images/";


    @Autowired
    private ArticleService articleService ;

    private ArticleUtil articleUtil= new ArticleUtil() ;

    //Create New Article
    @PostMapping(value="/articles" , consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Article> saveArticle(@RequestPart Article article, @RequestPart("image") MultipartFile multipartFile)throws IOException {
        String filePath = articleUtil.uploadFile(multipartFile);
        article.setImage(filePath);
        Article newArticle = articleService.saveArticle(article);

        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    //Get All Articles
    @GetMapping("/articles")
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    //Get Article by Id
    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable("id") Long idPath ){return articleService.getArticleById(idPath);}

    //Get article's image
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
}
