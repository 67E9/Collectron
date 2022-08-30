import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageCollectionViewComponent } from './manage-collection-view.component';

describe('ManageCollectionViewComponent', () => {
  let component: ManageCollectionViewComponent;
  let fixture: ComponentFixture<ManageCollectionViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageCollectionViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageCollectionViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
