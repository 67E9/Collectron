import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomOfferComponent } from './random-offer.component';

describe('RandomOfferComponent', () => {
  let component: RandomOfferComponent;
  let fixture: ComponentFixture<RandomOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandomOfferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RandomOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
