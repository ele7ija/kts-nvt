import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferingTableComponent } from './cultural-offering-table.component';

describe('CulturalOfferingTableComponent', () => {
  let component: CulturalOfferingTableComponent;
  let fixture: ComponentFixture<CulturalOfferingTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
