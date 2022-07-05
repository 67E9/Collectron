import {Component, OnInit} from '@angular/core';
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {Collectible} from "../../model/Collectible";

@Component({
  selector: 'app-sidebar-right-marketplace',
  templateUrl: './sidebar-right-marketplace.component.html',
  styleUrls: ['./sidebar-right-marketplace.component.css']
})
export class SidebarRightMarketplaceComponent implements OnInit {

  collectible: Collectible[] = [];
  showsAllCollectiblesForSale: boolean = true;
  oneRandomCollectible: Collectible = {
    id: 0,
    name: "",
    description: "",
    estimatedValue: 0,
    forSale: false,
    imageUrl: "",
    blogPosts: [],
  };

  constructor(private collectibleService: CollectiblesDBService) { }

  ngOnInit(): void {
    this.collectibleService.getCollectibleByForSale(this.showsAllCollectiblesForSale).subscribe(collectible => this.collectible = collectible);
    this.collectibleService.getCollectibleByForSale(this.showsAllCollectiblesForSale)
      .subscribe(oneRandomCollectible => {this.oneRandomCollectible = oneRandomCollectible[Math.floor(Math.random()*oneRandomCollectible.length)];
        Math.floor(Math.random()*oneRandomCollectible.length)});

  }

}
