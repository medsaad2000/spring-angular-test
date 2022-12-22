package lu.atozdigital.api.controller;


import lu.atozdigital.api.entity.Article;
import lu.atozdigital.api.entity.Order;
import lu.atozdigital.api.service.ArticleService;
import lu.atozdigital.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService ;
    @Autowired
    private ArticleService articleService ;

    //Create New Order
    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@RequestParam Long id){
       Article article = articleService.getArticleById(id);
       Order order = new Order();
       if(order.getArticles() == null){
           List<Article> as = new ArrayList<Article>();
           as.add(article);
           order.setArticles(as);
       }else{
           List<Article> as = order.getArticles();
           as.add(article);
           order.setArticles(as);
       }
       Order newOr = orderService.saveOrder(order);
        return new ResponseEntity<>(newOr, HttpStatus.CREATED);
    }

    //Get All Orders
    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    //Edit order
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> editArticle(@PathVariable Long id , @RequestParam Long idArticle){
        Order o = orderService.getOrderById(id);
        List<Article> listArticle = o.getArticles() ;
        Article a = articleService.getArticleById(idArticle);
        listArticle.add(a);
        o.setArticles(listArticle);

        orderService.saveOrder(o);
        return new ResponseEntity<>(o, HttpStatus.CREATED);

    }


}
