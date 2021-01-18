import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferingPageComponent } from './cultural-offering-page.component';

describe('CulturalOfferingPageComponent', () => {
  let component: CulturalOfferingPageComponent;
  let fixture: ComponentFixture<CulturalOfferingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
