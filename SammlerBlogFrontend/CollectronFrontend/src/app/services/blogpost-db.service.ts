import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {BlogPost} from "../model/BlogPost";

@Injectable({
  providedIn: 'root'
})
export class BlogpostDBService {

  constructor(private httpClient: HttpClient) { }

    getBlogPostById(id: number): Observable<BlogPost> {
      return this.httpClient.get<BlogPost>('http://localhost:8080/api/blogPost/' + id);
    }

    getAllBlogPosts(): Observable<BlogPost[]> {
      return this.httpClient.get<BlogPost[]>('http://localhost:8080/api/blogPost/');
    }

    getBlogPostsByTitleAndArticle(keyword: string): Observable<BlogPost[]> {
      return this.httpClient.get<BlogPost[]>('http://localhost:8080/api/blogPost/find/' + keyword);
    }

    getBlogPostsByCollectibleId (collectibleId: number): Observable<BlogPost[]> {
      return this.httpClient.get<BlogPost[]>('http://localhost:8080/api/blogPost/collectible/' + collectibleId);
    }

    newBlogPost(blogPost: BlogPost) {
      return this.httpClient.post<BlogPost>('http://localhost:8080/api/blogPost/', blogPost);
    }

    updateBlogPost(blogPost: BlogPost) {
      return this.httpClient.put<BlogPost>('http://localhost:8080/api/blogPost/' , blogPost);
    }

    deleteBlogPostById(id: number) {
      return this.httpClient.delete<BlogPost>('http://localhost:8080/api/blogPost/' + id);
    }
}

//TODO: add Error handling!
