import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Type} from "../../model/Type";
import {ActivatedRoute, Router} from "@angular/router";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {TypeService} from "../../services/type.service";
import {Collectible} from "../../model/Collectible";

@Component({
  selector: 'app-collectible-form',
  templateUrl: './collectible-form.component.html',
  styleUrls: ['./collectible-form.component.css']
})
export class CollectibleFormComponent implements OnInit {
  routeId?:number;
  collectibleForm: FormGroup = this.formBuilder.group({
    id: 0,
    name: ['', Validators.required],
    description: [''],
    estimatedValue: [0, Validators.min(0)],
    forSale: [false, Validators.required],
    imageUrl: ['', [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6}).*')]],
    type: ['', Validators.required],
  });
  // why does "collectibleForm?: FormGroup" not work?
  types: Type[] = [];
  typeEditingMode: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private collectibleService: CollectiblesDBService,
    private typeService: TypeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.routeId = Number(this.route.snapshot.paramMap.get('id'));

    if (this.routeId){

      this.collectibleService.getCollectibleById(this.routeId).subscribe(collectible => {

        this.collectibleForm = this.formBuilder.group({
          id: [collectible.id, Validators.required],
          name: [collectible.name, Validators.required],
          description: [collectible.description],
          estimatedValue: [collectible.estimatedValue, Validators.min(0)],
          forSale: [collectible.forSale, Validators.required],
          imageUrl: [collectible.imageUrl, [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6}).*')]],
          type: [collectible.type, Validators.required],
        });
      })
    } else {
      this.collectibleForm = this.formBuilder.group({
        name: ['', Validators.required],
        description: [''],
        estimatedValue: [0, Validators.min(0)],
        forSale: [false, Validators.required],
        imageUrl: ['', [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6}).*')]],
        type: ['', Validators.required],
      })
    }

    this.typeService.getAllType().subscribe(types => this.types = types);
  }

  onSubmit(formData: object){
    if (this.routeId){
      this.updateCollectible(formData as Collectible);
    } else if (!this.routeId){
      this.newCollectible(formData as Collectible);
    } else (
      console.log("form not set correctly")
    )
  }

  updateCollectible(updatedCollectible: Collectible){
    this.collectibleService.updateCollectible(updatedCollectible).subscribe(
      reply => {
        alert("Updated collectible: " + reply.name );
        this.router.navigate(['/collection', reply.id])
      })
  }

  newCollectible(newCollectible: Collectible){
    this.collectibleService.newCollectible(newCollectible).subscribe(
      reply => {
        alert("Created new collectible: " + reply.name + reply.id);
        this.router.navigate(['collection', reply.id])
      })
  }

  compareType(Type1: Type, Type2: Type) {
    if (!Type1 || !Type2) {
      return false;
    }
    return Type1.id == Type2.id;
  }

  toggleTypeForm(){
    this.typeEditingMode = !this.typeEditingMode;
  }

  OnTypeEvent(){
    this.toggleTypeForm()
    this.typeService.getAllType().subscribe(types => this.types = types);
  }

}

