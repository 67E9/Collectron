import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseCollectionListComponent } from './browse-collection-list.component';

describe('BrowseCollectionListComponent', () => {
  let component: BrowseCollectionListComponent;
  let fixture: ComponentFixture<BrowseCollectionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseCollectionListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrowseCollectionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
