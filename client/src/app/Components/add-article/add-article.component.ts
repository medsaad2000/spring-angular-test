import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder , FormControl, FormGroup , Validators } from '@angular/forms';
import { ArticleService } from "../../Services/article.service"
import { Article } from "../../Models/article.model"
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {
  //formArticle!: FormGroup;
  
  article !: Article;
  selectedFile : File | any;
  formArticle =new FormGroup({
    name : new FormControl('', [Validators.required, Validators.minLength(3)]),
    price : new FormControl('', [Validators.required, Validators.minLength(3)]),  
    image : new FormControl('', [Validators.required])

  })
  constructor(private formBuilder: FormBuilder, private http: HttpClient, private articleService:ArticleService ,private router: Router) { }

  ngOnInit(): void {
    this.formArticle = this.formBuilder.group({
      id:null,
      name:'',
      price:'',
      image: null
    });
    
  }
  submit():void{
    let ar : Article = {id:this.formArticle.get('id')?.value,name:this.formArticle.get('name')?.value , price:this.formArticle.get('price')?.value}
    const formData = new FormData();
    formData.append('image', this.selectedFile);
    formData.append('article', new Blob([JSON.stringify(ar)]
    ,{type:'application/json'}));
      this.saveData(formData);
    }

    onFileSelected(event) {
      if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.selectedFile = file;
      }
  } 

  saveData(formData : FormData){
    this.articleService.createArticle(formData).subscribe(res =>{
      this.router.navigate(['/'])
    });
  }


  }

  


