import { Component, OnInit } from '@angular/core';
import {TypeService} from "../../services/type.service";
import {Type} from "../../model/Type";

@Component({
  selector: 'app-type-list',
  templateUrl: './type-list.component.html',
  styleUrls: ['./type-list.component.css']
})
export class TypeListComponent implements OnInit {
  allTypes: Type[] =[];

  constructor(private typeService: TypeService) { }

  ngOnInit(): void {
    this.typeService.getAllType().subscribe(types => {
      this.allTypes = types;
      this.sortAlphabetically()
    })
  }

  deleteType(type: Type){
    if (type.id != null && type.collectibles.length === 0){
      this.typeService.deleteTypeById(type.id).subscribe(deletedType =>
        alert (`${deletedType.id} was deleted`))
        window.location.reload();
    }
  }

  sortBy(event: any){
    if (event.target.value === 'alphabetic_a-z') {
      this.sortAlphabetically();
    }
    if (event.target.value === 'alphabetic_z-a') {
      this.sortAlphabetically();
      this.allTypes = this.allTypes.reverse();
    }
  }

  compareTypeByName(a: Type, b: Type) {
    if (a.name.toLowerCase() < b.name.toLowerCase()) {return -1;}
    if (a.name.toLowerCase() > b.name.toLowerCase()) {return 1;}
    return 0;
  }

  sortAlphabetically(){
    this.allTypes.sort(this.compareTypeByName)
  }

}
