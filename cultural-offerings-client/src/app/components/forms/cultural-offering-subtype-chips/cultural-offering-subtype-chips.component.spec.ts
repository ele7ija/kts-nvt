import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferingSubtypeChipsComponent } from './cultural-offering-subtype-chips.component';

describe('CulturalOfferingSubtypeChipsComponent', () => {
  let component: CulturalOfferingSubtypeChipsComponent;
  let fixture: ComponentFixture<CulturalOfferingSubtypeChipsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingSubtypeChipsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingSubtypeChipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
