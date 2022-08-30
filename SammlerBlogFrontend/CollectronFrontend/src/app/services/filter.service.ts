import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  // Observable object source
  private objectsList = new Subject<any>();

  // Observable object streams
    objectsList$ =
      this.objectsList.asObservable();


  unfilteredList:any[] = [];
  chosenFilter:any[] = [];

  filteredList: any[] = [];

  searchText: string = "";
  selected_count:number = 1;

  constructor () { }

    //filter for filtered component list
  filterSidebar() {
    this.filteredList = this.unfilteredList.filter(f =>
        Object.values(f).toString().toLowerCase().includes(this.searchText.toLowerCase()));
    this.filterCheckboxes();
    this.objectsList.next(this.filteredList);
  }

  filterCheckboxes() {
    if (this.filteredList.some(i => i.selected)) {
      this.filteredList = this.filteredList.filter(s => s.selected)
    }
  }

    //HTML filter
  setSelectedItems(selectedItems:any) {
    this.chosenFilter = selectedItems;
  }

  getSelectedItems() {
    this.selected_count = 0;
    this.filteredList = this.unfilteredList.filter(i => {
      if(i.selected)
        this.selected_count++;

      return (i.selected);
    });
  }

  getItems() {
    return this.unfilteredList;
  }

  getSearchTerm() {
    return this.searchText;
  }

  setSearchTerm(term:string) {
    this.searchText = term;
  }

  //Clear term types by user
  clearFilter() {
    this.selected_count = 0;
    this.chosenFilter = this.unfilteredList.filter(i => {
      i.selected = false;
    this.filterSidebar();
    this.clearSelection();
      return (i.selected);

    });
  }

  //Clear selection
  clearSelection(){
    this.searchText = "";
  }

}
