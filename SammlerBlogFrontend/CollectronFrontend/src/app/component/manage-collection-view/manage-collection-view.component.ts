import { Component, OnInit } from '@angular/core';
import {Collectible} from "../../model/Collectible";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {ActivatedRoute} from "@angular/router";
import {BlogpostDBService} from "../../services/blogpost-db.service";
import {BlogPost} from "../../model/BlogPost";
import {FilterService} from "../../services/filter.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-manage-collection-view',
  templateUrl: './manage-collection-view.component.html',
  styleUrls: ['./manage-collection-view.component.css']
})
export class ManageCollectionViewComponent implements OnInit {
  allCollectibles: Collectible[] = [];
  allBlogPostsOfCollectibleById: BlogPost[] = [];

  collectibleRouteId?: number;
  selectedCollectible?: Collectible;
  selectedBlogPost?: BlogPost;

  subscription: Subscription;

  constructor(
    public filterService: FilterService,
    private collectibleService: CollectiblesDBService,
    private blogPostService: BlogpostDBService,
    private route: ActivatedRoute) {
        this.subscription = filterService.objectsList$
          .subscribe(allItems => {
            if(this.collectibleRouteId) {
              this.allBlogPostsOfCollectibleById = allItems
            } else {
              this.allCollectibles = allItems
            }
          })
  }

  ngOnInit(): void {
    this.collectibleRouteId = Number(this.route.snapshot.paramMap.get('id'));

    if(this.collectibleRouteId){
      this.blogPostService
        .getBlogPostsByCollectibleId(this.collectibleRouteId)
        .subscribe(blogPostsByCollectibleId => {this.allBlogPostsOfCollectibleById = blogPostsByCollectibleId;
          this.sortAlphabetically();
            this.filterService.unfilteredList = blogPostsByCollectibleId;})
      } else {
      this.collectibleService.getAllCollectible().subscribe(collectible => { this.allCollectibles = collectible
        this.sortAlphabetically();
        this.filterService.unfilteredList = collectible;})
      }
  }

  selectItemForSideBar(item: any) {
    if(this.collectibleRouteId) {
      this.selectedBlogPost = item as BlogPost;
    } else {
      this.selectedCollectible = item as Collectible;
    }
  }

  sortBy(event: any){
    if (this.collectibleRouteId){
      this.sortBlogPosts(event);
    } else {
      this.sortCollectibles(event);
    }
  }

  sortBlogPosts(event: any){
    if (event.target.value === 'alphabetic_a-z') {
      this.sortAlphabetically();
    }
    if (event.target.value === 'alphabetic_z-a') {
      this.sortAlphabetically();
      this.allBlogPostsOfCollectibleById = this.allBlogPostsOfCollectibleById.reverse();
    }
    if (event.target.value === 'newest') {
      this.sortBlogPostsByDate()
      this.allBlogPostsOfCollectibleById = this.allBlogPostsOfCollectibleById.reverse();
    }
    if (event.target.value === 'oldest') {
      this.sortBlogPostsByDate()
    }
  }

  sortCollectibles(event: any){
    if (event.target.value === 'alphabetic_a-z') {
      this.sortAlphabetically();
    }
    if (event.target.value === 'alphabetic_z-a') {
      this.sortAlphabetically();
      this.allCollectibles = this.allCollectibles.reverse();
    }
  }

  sortAlphabetically(){
    if (this.collectibleRouteId) {
      this.allBlogPostsOfCollectibleById = this.allBlogPostsOfCollectibleById.sort(this.compareBlogPostByTitle);
    } else {
      this.allCollectibles = this.allCollectibles.sort(this.compareCollectibleByName)
    }
  }

  compareCollectibleByName(a: Collectible, b: Collectible) {
    if (a.name.toLowerCase() < b.name.toLowerCase()) {return -1;}
    if (a.name.toLowerCase() > b.name.toLowerCase()) {return 1;}
    return 0;
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

  sortBlogPostsByDate(){
    this.allBlogPostsOfCollectibleById.sort(this.compareBlogPostsByDate)
  }

  compareBlogPostsByDate(a: BlogPost, b: BlogPost){
    if (a.timeStamp && b.timeStamp) {
      if (a.timeStamp < b.timeStamp){return -1;}
      if (a.timeStamp > b.timeStamp){return 1;}
    }
    return 0;
  }
}
