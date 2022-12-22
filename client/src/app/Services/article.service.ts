import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Article} from "../Models/article.model";
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private _url: string = "http://localhost:8080"
  constructor(private http: HttpClient) { 

  }
  getAricles(): any {
    return this.http.get<Article>(this._url +"/articles");
  }

  createArticle(formData : FormData): Observable<any>{
    return this.http.post<Article>(
      this._url+'/articles',formData
    ).pipe(map((data : any) =>{
      return data;
    }))
  }

  getImage(filename : string):any{
    return this.http.get<any>(this._url +"/downloads/"+filename);
  }

  getArticleById(id : number):any{
    return this.http.get<Article>(this._url +"/articles");
  }
}
