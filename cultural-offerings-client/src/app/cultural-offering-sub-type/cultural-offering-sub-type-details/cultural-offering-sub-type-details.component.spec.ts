import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { CulturalOfferingSubTypeRoutingModule } from '../cultural-offering-sub-type-routing.module';
import { CulturalOfferingSubTypeDetailsComponent } from './cultural-offering-sub-type-details.component';
import {of} from 'rxjs';


describe('CulturalOfferingSubTypeDetailsComponent', () => {
  let component: CulturalOfferingSubTypeDetailsComponent;
  let fixture: ComponentFixture<CulturalOfferingSubTypeDetailsComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group').and.returnValue({value: {}})
  };
  const snackbarStub = jasmine.createSpy('snackbarStub');
  const culturalOfferingSubTypeServiceStub = {
    insert() {
      return of(null);
    },
    update() {
      return of(null);
    },
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingSubTypeDetailsComponent ],
      imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),

        MatIconModule,
        MatChipsModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,

        TableModule,

        CulturalOfferingSubTypeRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: CulturalOfferingSubtypeService, useValue: culturalOfferingSubTypeServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatSnackBar, useValue: snackbarStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingSubTypeDetailsComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
    component.culturalOfferingSubType = {id: 1};
  });

  afterAll(() => {

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.culturalOfferingSubTypeService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should attempt update', async () => {
    spyOn(culturalOfferingSubTypeServiceStub, 'update').and.returnValue(of(null));
    await component.getUpdateCulturalOfferingSubTypePromises();
    expect(culturalOfferingSubTypeServiceStub.update).toHaveBeenCalled();
  });

  it('should attempt insert', async () => {
    spyOn(culturalOfferingSubTypeServiceStub, 'insert').and.returnValue(of(null));
    await component.getInsertCulturalOfferingSubTypePromises();
    expect(culturalOfferingSubTypeServiceStub.insert).toHaveBeenCalled();
  });

  it('should fail insert', async () => {
    spyOn(component, 'getInsertCulturalOfferingSubTypePromises').and.returnValue(Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.culturalOfferingSubType);
  });

  it('should pass insert', async () => {
    const param = {id: 1};
    spyOn(component, 'getInsertCulturalOfferingSubTypePromises').and.returnValue(Promise.resolve(param));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(param);
  });

  it('should fail update', async () => {
    spyOn(component, 'getUpdateCulturalOfferingSubTypePromises').and.returnValue(Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.culturalOfferingSubType);
  });

  it('should pass update', async () => {
    const param = {id: 1};
    spyOn(component, 'getUpdateCulturalOfferingSubTypePromises').and.returnValue(Promise.resolve(param));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(param);
  });
});
