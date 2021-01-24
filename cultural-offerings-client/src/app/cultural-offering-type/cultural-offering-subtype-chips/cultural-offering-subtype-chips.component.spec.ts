import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { TableModule } from 'src/app/shared/modules/table/table.module';
import { CulturalOfferingTypeRoutingModule } from '../cultural-offering-type-routing.module';

import { CulturalOfferingSubtypeChipsComponent } from './cultural-offering-subtype-chips.component';

describe('CulturalOfferingSubtypeChipsComponent', () => {
  let component: CulturalOfferingSubtypeChipsComponent;
  let fixture: ComponentFixture<CulturalOfferingSubtypeChipsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingSubtypeChipsComponent ],
      imports: [
        CommonModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        MatIconModule,
        MatChipsModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        TableModule,
        CulturalOfferingTypeRoutingModule
      ]
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

  it('should emit add event', () => {
    spyOn(component, 'add');
    component.add({input: null, value: 'something'});
    expect(component.add).toHaveBeenCalled();
  });
});
