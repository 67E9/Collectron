import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlogpostDBService} from "../../services/blogpost-db.service";
import {Collectible} from "../../model/Collectible";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {BlogPost} from "../../model/BlogPost";

@Component({
  selector: 'app-blog-post-form',
  templateUrl: './blog-post-form.component.html',
  styleUrls: ['./blog-post-form.component.css']
})
export class BlogPostFormComponent implements OnInit {
  routeId?:number;
  blogPostForm: FormGroup = this.formBuilder.group({
    id: [0, Validators.required],
    title: ['', Validators.required],
    article: ['', Validators.required],
    collectible: ['', Validators.required]
  });
  collectibles: Collectible[] = [];

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private blogPostService: BlogpostDBService,
    private collectibleService: CollectiblesDBService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.routeId = Number(this.route.snapshot.paramMap.get('id'));

    if (this.routeId){

      this.blogPostService.getBlogPostById(this.routeId).subscribe(post => {
        this.blogPostForm = this.formBuilder.group({
          id: [post.id, Validators.required],
          title: [post.title, Validators.required],
          article: [post.article, Validators.required],
          collectible: [post.collectible, Validators.required]
         });
      })
    } else {

      this.blogPostForm= this.formBuilder.group({
        title: ['', Validators.required],
        article: ['', Validators.required],
        collectible: ['', Validators.required]
      })
    }

    this.collectibleService.getAllCollectible().subscribe(collectibles => this.collectibles = collectibles);
  }

  onSubmit(formData: object){
    if (this.routeId){
      this.updateBlogPost(formData as BlogPost);
    } else if (!this.routeId){
      this.newBlogPost(formData as BlogPost);
    } else (
      console.log("form not set correctly")
    )
  }

  newBlogPost(newPost: BlogPost){
    this.blogPostService.newBlogPost(newPost).subscribe(reply => {
      alert("Created new Blog Post: " + reply.title);
      this.router.navigate(['/collection', reply.collectible.id]);
    });
  }

  updateBlogPost(updatedPost: BlogPost){
    this.blogPostService.updateBlogPost(updatedPost).subscribe(reply => {
      alert("Updated Blog Post: " + reply.title + "\n\n");
      this.router.navigate(['/collection', reply.collectible.id]);
    });
  }

  compareCollectibles(collectible1: Collectible, collectible2: Collectible) {
    if (!collectible1 || !collectible2) {
      return false;
    }
    return collectible1.id == collectible2.id;
  }

}
