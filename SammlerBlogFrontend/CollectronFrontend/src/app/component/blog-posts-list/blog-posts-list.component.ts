import {Component, Input, OnInit} from '@angular/core';
import {BlogPost} from "../../model/BlogPost";
import {FilterService} from "../../services/filter.service";
import {BlogpostDBService} from "../../services/blogpost-db.service";

@Component({
  selector: 'app-blog-posts-list',
  templateUrl: './blog-posts-list.component.html',
  styleUrls: ['./blog-posts-list.component.css']
})
export class BlogPostsListComponent implements OnInit {

  @Input() allItems: BlogPost[] = [];

  constructor(private filterService: FilterService,
              private blogPostService: BlogpostDBService) {
  }

  ngOnInit(): void {
    this.blogPostService.getAllBlogPosts().subscribe(blogPost => {
      this.allItems = blogPost;
      this.sortByDate();
      this.allItems = this.allItems.reverse();
      this.filterService.unfilteredList = blogPost;
    })
  }

  sortAlphabetically() {
    this.allItems = this.allItems.sort(this.compareBlogPostByTitle);
  }

  compareBlogPostByTitle(a: BlogPost, b: BlogPost) {
    if (a.title.toLowerCase() < b.title.toLowerCase()) {
      return -1;
    }
    if (a.title.toLowerCase() > b.title.toLowerCase()) {
      return 1;
    }
    return 0;
  }

  sortByDate(){
    this.allItems.sort(this.compareBlogPostsByDate);
  }

  compareBlogPostsByDate(a: BlogPost, b: BlogPost){
    if (a.timeStamp && b.timeStamp) {
      if (a.timeStamp < b.timeStamp){return -1;}
      if (a.timeStamp > b.timeStamp){return 1;}
    }
    return 0;
  }

  sortBy(event: any) {
    if (event.target.value === 'alphabetic_a-z') {
      this.sortAlphabetically();
    }
    if (event.target.value === 'alphabetic_z-a') {
      this.sortAlphabetically();
      this.allItems = this.allItems.reverse();
    }
    if (event.target.value === 'newest') {
      this.sortByDate()
      this.allItems = this.allItems.reverse();
    }
    if (event.target.value === 'oldest') {
      this.sortByDate()
    }
  }

}
