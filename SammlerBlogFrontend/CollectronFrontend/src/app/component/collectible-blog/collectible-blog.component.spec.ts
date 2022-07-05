import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectibleBlogComponent } from './collectible-blog.component';

describe('CollectibleBlogComponent', () => {
  let component: CollectibleBlogComponent;
  let fixture: ComponentFixture<CollectibleBlogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollectibleBlogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollectibleBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
