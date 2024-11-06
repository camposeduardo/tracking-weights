import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarProgressComponent } from './navbar-progress.component';

describe('NavbarProgressComponent', () => {
  let component: NavbarProgressComponent;
  let fixture: ComponentFixture<NavbarProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarProgressComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavbarProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
