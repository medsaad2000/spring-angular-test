import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../Models/order.model';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private _url: string = "http://localhost:8080"
  constructor(private http: HttpClient) { }

  getAllOrders(): any {
    return this.http.get<Order>(this._url +"/orders");
  }

  createOrder(formData : FormData): Observable<any>{
    return this.http.post<Order>(
      this._url+'/orders',formData
    ).pipe(map((data : any) =>{
      return data;
    }))
  }
}
