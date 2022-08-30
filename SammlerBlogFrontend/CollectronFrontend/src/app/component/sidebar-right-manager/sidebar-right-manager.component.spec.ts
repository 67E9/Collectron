import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarRightManagerComponent } from './sidebar-right-manager.component';

describe('SidebarRightManagerComponent', () => {
  let component: SidebarRightManagerComponent;
  let fixture: ComponentFixture<SidebarRightManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarRightManagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarRightManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
