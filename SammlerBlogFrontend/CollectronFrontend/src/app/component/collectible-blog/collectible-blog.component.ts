import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BlogpostDBService} from "../../services/blogpost-db.service";
import {BlogPost} from "../../model/BlogPost";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {Collectible} from "../../model/Collectible";

@Component({
  selector: 'app-collectible-blog',
  templateUrl: './collectible-blog.component.html',
  styleUrls: ['./collectible-blog.component.css']
})
export class CollectibleBlogComponent implements OnInit {
  routeId?: number;
  selectedCollectible?: Collectible;
  selectedBlogPosts?: BlogPost[]

  constructor(
    private route: ActivatedRoute,
    private blogPostService: BlogpostDBService,
    private collectibleService: CollectiblesDBService
  ) { }

  ngOnInit(): void {
    this.routeId = Number(this.route.snapshot.paramMap.get('id'));
    this.collectibleService.getCollectibleById(this.routeId).subscribe(collectible => {
      this.selectedCollectible = collectible;
    });
    this.blogPostService.getBlogPostsByCollectibleId(this.routeId). subscribe(blogPosts => {
      this.selectedBlogPosts = blogPosts;
      this.selectedBlogPosts = this.selectedBlogPosts
        .sort((a,b) => this.compareBlogPostByTimeStamp(a,b))
        .reverse()
    });

  }

  compareBlogPostByTimeStamp(a: BlogPost, b: BlogPost) {
    if (a.timeStamp && b.timeStamp) {
      if (a.timeStamp < b.timeStamp) {
        return -1;
      }
      if (a.timeStamp > b.timeStamp) {
        return 1;
      }
    }
    return 0;
  }

}
