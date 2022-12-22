import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes , RouterModule} from '@angular/router'
import { ArticlesComponent } from './Components/articles/articles.component';
import { AddArticleComponent } from './Components/add-article/add-article.component';
import { OrdersComponent } from './Components/orders/orders.component';

const routes: Routes = [
  { path: '', component: ArticlesComponent },
  { path: 'addArticle', component: AddArticleComponent },
  { path: 'orders', component: OrdersComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 