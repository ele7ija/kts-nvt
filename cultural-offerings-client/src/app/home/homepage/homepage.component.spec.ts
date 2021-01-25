import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ElementRef } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GoogleMapsModule } from '@angular/google-maps';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingService } from 'src/app/core/services/cultural-offering/cultural-offering.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { HomeRoutingModule } from '../home-routing.module';
import { HomepageComponent } from './homepage.component';
import {of} from 'rxjs';

describe('HomepageComponent', () => {
  let component: HomepageComponent;
  let fixture: ComponentFixture<HomepageComponent>;

  const culturalOfferingServiceStub = {
    getAll: jasmine.createSpy('getAllCulturalOfferings').and.returnValue(of({content: [{}, {}, {}]}))
  };
  const culturalOfferingTypeServiceStub = {
    getAll: jasmine.createSpy('getAllCulturalOfferingTypes').and.returnValue(of({content: [{}, {}]}))
  };
  const culturalOfferingSubtypeServiceStub = {
    getAllByTypeId: jasmine.createSpy('getAllCulturalOfferingSubtypes').and.returnValue(of([]))
  };
  const authServiceStub = {
    isLoggedIn: jasmine.createSpy('isLoggedIn').and.returnValue(true),
    getUserRole: jasmine.createSpy('getUserRole').and.returnValue('ADMIN'),
  };
  const formBuilderStub = {
    group: jasmine.createSpy('formBuilder').and.returnValue({
      patchValue: jasmine.createSpy('patchValue').and.callFake(() => {}),
      addControl: jasmine.createSpy('patchValue').and.callFake(() => {}),
      value: {
        termField: '',
        subscriptionsField: ''
      }
    })
  };
  const routerStub = {};
  const elementRefStub = {};
  const httpClientServiceStub = {};
  const apiServiceStub = {};

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomepageComponent ],
      imports: [
        CommonModule,
        HomeRoutingModule,
        GoogleMapsModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        MatSnackBarModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatIconModule,
        MatChipsModule,
        MatFormFieldModule,
        MatProgressSpinnerModule
      ],
      providers: [
        {provide: CulturalOfferingService, useValue: culturalOfferingServiceStub},
        {provide: CulturalOfferingTypeService, useValue: culturalOfferingTypeServiceStub},
        {provide: CulturalOfferingSubtypeService, useValue: culturalOfferingSubtypeServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientServiceStub},
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: Router, useValue: routerStub},
        {provide: ElementRef, useValue: elementRefStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.culturalOfferingService).toBeTruthy();
    expect(component.culturalOfferingTypeService).toBeTruthy();
    expect(component.culturalOfferingSubtypeService).toBeTruthy();
    expect(component.authService).toBeTruthy();
    expect(component.formBuilder).toBeTruthy();
    expect(component.router).toBeTruthy();
    expect(component.elRef).toBeTruthy();
  });

  it('should fetch cultural offerings', async () => {
    component.culturalOfferings = [];
    await component.fetchAllCulturalOffering();
    expect(component.culturalOfferings.length).toBe(3);
  });

  it('should fetch cultural offering types', async () => {
    component.culturalOfferingTypes = [];
    spyOn(component, 'fetchAllCulturalOfferingSubtypes').and.callFake(() => {});
    await component.fetchAllCulturalOfferingTypes();
    expect(component.culturalOfferingTypes.length).toBe(2);
  });
});
