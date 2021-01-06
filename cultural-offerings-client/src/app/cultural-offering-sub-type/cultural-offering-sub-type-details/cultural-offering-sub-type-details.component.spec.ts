import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferingSubTypeDetailsComponent } from './cultural-offering-sub-type-details.component';

describe('CulturalOfferingSubTypeDetailsComponent', () => {
  let component: CulturalOfferingSubTypeDetailsComponent;
  let fixture: ComponentFixture<CulturalOfferingSubTypeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingSubTypeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingSubTypeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
