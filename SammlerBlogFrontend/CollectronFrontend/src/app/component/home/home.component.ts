import {FilterService} from "../../services/filter.service";
import {Component, OnInit} from '@angular/core';
import {BlogPost} from "../../model/BlogPost";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  allBlogPosts: BlogPost[] = [];
  subscription: Subscription;

  constructor(public filterService: FilterService) {
      this.subscription = filterService.objectsList$
        .subscribe(allBlogPosts =>  this.allBlogPosts = allBlogPosts);
  }

  ngOnInit(): void {
  }
}
