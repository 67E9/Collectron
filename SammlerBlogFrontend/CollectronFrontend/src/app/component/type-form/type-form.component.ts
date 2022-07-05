import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TypeService} from "../../services/type.service";
import {Type} from "../../model/Type";
import { Location} from '@angular/common';

@Component({
  selector: 'app-type-form',
  templateUrl: './type-form.component.html',
  styleUrls: ['./type-form.component.css']
})
export class TypeFormComponent implements OnInit {
  routeId?:number;
  typeForm: FormGroup = this.formBuilder.group({
    id: 0,
    name: ['', Validators.required],
    description: ['']
  });
  @Output() newTypeEvent: EventEmitter<boolean> = new EventEmitter<boolean>()

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private typeService: TypeService,
    private router: Router,
    private location: Location,
  ) { }

  ngOnInit(): void {

    this.routeId = Number(this.route.snapshot.paramMap.get('id'));

      if (this.routeId){

        this.typeService.getTypeById(this.routeId).subscribe( type => {
            this.typeForm = this.formBuilder.group({
              id: [type.id],
              name: [type.name, Validators.required],
              description: [type.description]
            })
          }
        )

      } else {

        this.typeForm = this.formBuilder.group({
          name: ['', Validators.required],
          description: ['']
        })

      }
    }

  onSubmit(formData: object){
      if (this.routeId) {
        this.updateType(formData as Type);
      } else if (!this.routeId) {
        this.newType(formData as Type);
      } else {
        console.log("form not set correctly")
      }
    }

  newType(newType: Type){
      this.typeService.newType(newType).subscribe(reply => {
        alert("Created new Type: " + reply.name);
        if (this.route.snapshot.url[0].path === 'new_collectible'){
          this.newTypeEvent.emit(false)
        } else {
          this.router.navigate(['types']);
        }
        // this.location.back();Route(url:'new_collectible', path:'new_collectible')
      })
    }

  updateType(updatedType: Type){
    this.typeService.updateType(updatedType).subscribe(reply => {
      alert("Updated Type: " + reply.name);
      // this.router.navigate(['types']);
      this.location.back();
    })
  }

}
