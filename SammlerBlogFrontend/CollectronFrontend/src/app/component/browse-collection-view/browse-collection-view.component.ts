import { Component, OnInit } from '@angular/core';
import {Collectible} from "../../model/Collectible";
import {Subscription} from "rxjs";
import {FilterService} from "../../services/filter.service";

@Component({
  selector: 'app-browse-collection-view',
  templateUrl: './browse-collection-view.component.html',
  styleUrls: ['./browse-collection-view.component.css']
})
export class BrowseCollectionViewComponent implements OnInit {

  allCollectibles: Collectible[] = [];
  subscription: Subscription;

  constructor(public filterService: FilterService) {
      this.subscription = filterService.objectsList$
        .subscribe(collectible => this.allCollectibles = collectible);
  }

  ngOnInit(): void {
  }

}
