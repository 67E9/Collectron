import {BlogpostDBService} from "../../services/blogpost-db.service";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {BlogPost} from "../../model/BlogPost";
import {Collectible} from "../../model/Collectible";
import {Type} from "../../model/Type";
import {TypeService} from "../../services/type.service";
import {Component, OnInit} from "@angular/core";
import {Subscription } from "rxjs";
import {FilterService} from "../../services/filter.service";

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  keyword = '';

  searchedBlogPosts?: BlogPost[];
  searchedCollectibles?: Collectible[];
  searchedTypes?: Type[];

  constructor(
    private blogPostService: BlogpostDBService,
    private collectibleService: CollectiblesDBService,
    private typeService: TypeService) {  }

  ngOnInit(): void {
    this.blogPostService.getBlogPostsByTitleAndArticle(history.state.data)
      .subscribe(searchedBlogPost =>  this.searchedBlogPosts = searchedBlogPost);

    this.collectibleService.getCollectibleByNameAndDescription(history.state.data)
      .subscribe(searchedCollectible => this.searchedCollectibles = searchedCollectible);

    this.typeService.getTypeByNameAndDescription(history.state.data)
      .subscribe(searchedType => this.searchedTypes = searchedType);
  }

  addItem(keyword: string) {
    this.keyword = keyword
    this.fetchSearchList();
  }

  fetchSearchList() {
    this.blogPostService.getBlogPostsByTitleAndArticle(this.keyword)
      .subscribe(searchedBlogPost => this.searchedBlogPosts = searchedBlogPost)
    this.collectibleService.getCollectibleByNameAndDescription(this.keyword)
      .subscribe(searchedCollectibles => this.searchedCollectibles = searchedCollectibles)
    this.typeService.getTypeByNameAndDescription(this.keyword)
      .subscribe(searchedTypes => this.searchedTypes = searchedTypes)
  }

}
