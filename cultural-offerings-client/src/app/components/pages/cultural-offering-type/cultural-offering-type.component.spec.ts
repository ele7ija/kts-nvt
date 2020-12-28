import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferingTypeComponent } from './cultural-offering-type.component';

describe('CulturalOfferingTypeComponent', () => {
  let component: CulturalOfferingTypeComponent;
  let fixture: ComponentFixture<CulturalOfferingTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
