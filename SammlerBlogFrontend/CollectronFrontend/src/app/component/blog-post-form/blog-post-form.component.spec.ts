import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlogPostFormComponent } from './blog-post-form.component';

describe('BlogPostFormComponent', () => {
  let component: BlogPostFormComponent;
  let fixture: ComponentFixture<BlogPostFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlogPostFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BlogPostFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
