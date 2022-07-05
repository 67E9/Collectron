import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarRightMarketplaceComponent } from './sidebar-right-marketplace.component';

describe('SidebarRightMarketplaceComponent', () => {
  let component: SidebarRightMarketplaceComponent;
  let fixture: ComponentFixture<SidebarRightMarketplaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebarRightMarketplaceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebarRightMarketplaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
