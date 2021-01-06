import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferingSubTypeComponent } from './cultural-offering-sub-type.component';

describe('CulturalOfferingSubTypeComponent', () => {
  let component: CulturalOfferingSubTypeComponent;
  let fixture: ComponentFixture<CulturalOfferingSubTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingSubTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingSubTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
