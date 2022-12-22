import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ArticlesComponent } from './Components/articles/articles.component';
import { AddArticleComponent } from './Components/add-article/add-article.component';
import { NavComponent } from './Components/nav/nav.component';
import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrdersComponent } from './Components/orders/orders.component';


@NgModule({
  declarations: [
    AppComponent,
    ArticlesComponent,
    AddArticleComponent,
    NavComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
