import {Component, Input, OnInit} from '@angular/core';
import {Collectible} from "../../model/Collectible";
import {BlogPost} from "../../model/BlogPost";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {BlogpostDBService} from "../../services/blogpost-db.service";

@Component({
  selector: 'app-sidebar-right-manager',
  templateUrl: './sidebar-right-manager.component.html',
  styleUrls: ['./sidebar-right-manager.component.css']
})
export class SidebarRightManagerComponent implements OnInit {

 @Input() collectible?: Collectible;
 @Input() blogPost?: BlogPost;

  constructor(
    private collectibleService: CollectiblesDBService,
    private blogPostService: BlogpostDBService,
  ) { }

  ngOnInit(): void {

  }

  deleteOnClick() {
    if(this.collectible) {
      if (this.collectible.id != null && this.collectible.blogPosts.length === 0) {
        if (confirm("Do you really want to delete " + this.collectible.name + "?")){
          this.collectibleService.deleteCollectibleById(this.collectible.id)
            .subscribe((deletedCollectible) => {
              alert(`${deletedCollectible.name} was deleted`);
              window.location.reload();
            });

        }
      }
    } else if (this.blogPost) {
      if (this.blogPost.id != null) {
        if (confirm("Do you really want to delete " + this.blogPost.title + "?")){
          this.blogPostService.deleteBlogPostById(this.blogPost.id)
            .subscribe(deletedBlogPost => {
              alert(`${deletedBlogPost.title} was deleted`);
              window.location.reload();
            })
        }
      }
    }
  }


}
