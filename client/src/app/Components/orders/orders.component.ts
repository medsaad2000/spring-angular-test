import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/Services/order.service';
import { Order } from "../../Models/order.model"
import {DatePipe, formatDate} from '@angular/common';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  public orders : Order[] |  any ;
  public total : any;
  datePipe = new DatePipe('en-US');
  constructor(public orderService : OrderService) { }

  ngOnInit(): void {
    this.getOrders();
  }

  public getOrders() : void{
    this.orderService.getAllOrders().subscribe(
      (response : Order[]) =>{
        this.orders = response ;
        console.log(this.orders);
      }, (error: HttpErrorResponse) => {

        alert(error.message);
      }
    );

  }

}
