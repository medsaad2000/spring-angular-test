import { Component, OnInit } from '@angular/core';
import { ArticleService } from "../../Services/article.service"
import { Article } from "../../Models/article.model"
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { OrderService } from 'src/app/Services/order.service';
import { Order } from 'src/app/Models/order.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {
  public articles: Article[] | any;

  constructor(private articleService: ArticleService , private orderService : OrderService , private http: HttpClient,private router: Router ) { }

  ngOnInit(): void {
    this.getArticles();

  }
  public getArticles(): void {
    this.articleService.getAricles().subscribe(
      (response: Article[]) => {

        this.articles = response;

        console.log(this.articles[0])

      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



  public getUrl(url: string): string {
    url.replace(/\/\//, '/');
    var filename = url.replace(/^.*[\\\/]/, '')
    return 'http://localhost:8080/download/' + filename;
  }

  public orderArticle(id: any){
    const formdata = new FormData();
    formdata.append('id',id);

    this.orderService.createOrder(formdata).subscribe((response: Order) => {
      alert('Added successfully')
      this.router.navigate(['/orders'])

    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
    
  }



}
