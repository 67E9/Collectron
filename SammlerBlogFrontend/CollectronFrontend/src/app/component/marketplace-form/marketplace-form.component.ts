import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CollectiblesDBService} from "../../services/collectibles-db.service";
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'app-marketplace-form',
  templateUrl: './marketplace-form.component.html',
  styleUrls: ['./marketplace-form.component.css']
})
export class MarketplaceFormComponent implements OnInit {

  requestCollectibleImageUrl = '';
  collectibleIdForRequest:number = 0;
  collectibleForSaleRequest: FormGroup = this.formBuilder.group({
    collectibleId: this.collectibleIdForRequest = Number(this.route.snapshot.paramMap.get('id')),
    email: ['', [Validators.email, Validators.required]],
    message: ['' , Validators.required],
    autoResponse: false
  })


  constructor(private route: ActivatedRoute,
              private collectibleService: CollectiblesDBService,
              private formBuilder: FormBuilder,
              private contactService: ContactService
  ) { }


  ngOnInit(): void {
    this.collectibleIdForRequest = Number(this.route.snapshot.paramMap.get('id'));
    this.collectibleService.getCollectibleById(this.collectibleIdForRequest).subscribe(collectible => this.requestCollectibleImageUrl = collectible.imageUrl);
  }

  onSubmit(collectibleForSaleRequest: any) {
    this.contactService.requestMessage(this.collectibleForSaleRequest = collectibleForSaleRequest).subscribe(
      response => {
        alert("Message sent!")},
      error => {
        alert("Error. Not sent!")
      }
    )
  }
}
