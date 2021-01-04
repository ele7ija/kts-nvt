import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CultrualOfferingTypeDetailsComponent } from './cultrual-offering-type-details.component';

describe('CultrualOfferingTypeDetailsComponent', () => {
  let component: CultrualOfferingTypeDetailsComponent;
  let fixture: ComponentFixture<CultrualOfferingTypeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CultrualOfferingTypeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CultrualOfferingTypeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
