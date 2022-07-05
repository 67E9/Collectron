import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {FilterService} from "../../services/filter.service";
import {Component, Input, OnInit} from '@angular/core';
import {Collectible} from "../../model/Collectible";

@Component({
  selector: 'app-browse-collection-list',
  templateUrl: './browse-collection-list.component.html',
  styleUrls: ['./browse-collection-list.component.css']
})
export class BrowseCollectionListComponent implements OnInit {

  @Input() allItems: Collectible[] = [];

  constructor(private collectibleService: CollectiblesDBService,
              public filterService: FilterService) { }

  ngOnInit(): void {
    this.collectibleService.getAllCollectible().subscribe(collectible => {
      this.allItems = collectible;
      this.sortAlphabetically()
      this.filterService.unfilteredList = collectible
    })
  }

  compareCollectibleByName(a: Collectible, b: Collectible) {
    if (a.name.toLowerCase() < b.name.toLowerCase()) {return -1;}
    if (a.name.toLowerCase() > b.name.toLowerCase()) {return 1;}
    return 0;
  }

  sortAlphabetically(){
    this.allItems.sort(this.compareCollectibleByName)
  }

  sortBy(event: any){
    if (event.target.value === 'alphabetic_a-z') {
      this.sortAlphabetically();
    }
    if (event.target.value === 'alphabetic_z-a') {
      this.sortAlphabetically();
      this.allItems = this.allItems.reverse();
    }
  }

}
