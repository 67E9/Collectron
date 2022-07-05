import { Component, OnInit } from '@angular/core';
import {Collectible} from "../../model/Collectible";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {FilterService} from "../../services/filter.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-marketplace',
  templateUrl: './marketplace.component.html',
  styleUrls: ['./marketplace.component.css']
})
export class MarketplaceComponent implements OnInit {

  allCollectiblesForSale: Collectible[] = [];
  showsAllCollectiblesForSale: boolean = true;
  subscription: Subscription;

  constructor(public filterService: FilterService,
              private collectibleService: CollectiblesDBService) {
      this.subscription = filterService.objectsList$
        .subscribe(collectible => {this.allCollectiblesForSale = collectible;
          });
  }

  ngOnInit(): void {
      this.collectibleService.getCollectibleByForSale(this.showsAllCollectiblesForSale)
        .subscribe(collectible => { this.allCollectiblesForSale = collectible;
          this.sortAlphabetically()
      this.filterService.unfilteredList = collectible})
  }

  //TODO compare in components can be refactored to separate file for re-use
  compareCollectibleByName(a: Collectible, b: Collectible) {
    if (a.name.toLowerCase() < b.name.toLowerCase()) {return -1;}
    if (a.name.toLowerCase() > b.name.toLowerCase()) {return 1;}
    return 0;
  }


  compareCollectibleByEstimatedValue(a: Collectible, b: Collectible) {
    if (a.estimatedValue < b.estimatedValue) {return -1;}
    if (a.estimatedValue > b.estimatedValue) {return 1;}
    return 0;
  }

  sortAlphabetically() {
    this.allCollectiblesForSale = this.allCollectiblesForSale.sort(this.compareCollectibleByName)
  }

  sortByEstimatedValues() {
    this.allCollectiblesForSale = this.allCollectiblesForSale.sort(this.compareCollectibleByEstimatedValue)
  }

  sortBy(event: any) {
    if (event.target.value === 'alphabetic_a-z') {
      this.sortAlphabetically();
    }
    if (event.target.value === 'alphabetic_z-a') {
      this.sortAlphabetically();
      this.allCollectiblesForSale = this.allCollectiblesForSale.reverse();
    }
    if (event.target.value === 'value_low_to_high') {
      this.sortByEstimatedValues();
    }
    if (event.target.value === 'value_high_to_low') {
      this.sortByEstimatedValues();
      this.allCollectiblesForSale = this.allCollectiblesForSale.reverse();
    }
  }

}
