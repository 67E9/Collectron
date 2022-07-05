import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectibleFormComponent } from './collectible-form.component';

describe('CollectibleFormComponent', () => {
  let component: CollectibleFormComponent;
  let fixture: ComponentFixture<CollectibleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollectibleFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollectibleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
