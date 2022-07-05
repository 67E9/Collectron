import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseCollectionViewComponent } from './browse-collection-view.component';

describe('BrowseCollectionViewComponent', () => {
  let component: BrowseCollectionViewComponent;
  let fixture: ComponentFixture<BrowseCollectionViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseCollectionViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrowseCollectionViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
