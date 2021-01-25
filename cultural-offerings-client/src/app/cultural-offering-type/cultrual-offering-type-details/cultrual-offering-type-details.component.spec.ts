import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { CulturalOfferingTypeRoutingModule } from '../cultural-offering-type-routing.module';
import { CultrualOfferingTypeDetailsComponent } from './cultrual-offering-type-details.component';
import {of} from 'rxjs';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { ImageService } from 'src/app/core/services/image/image.service';
import { ListChangeEventType } from 'src/app/core/model/list-change-event-type.enum';

describe('CultrualOfferingTypeDetailsComponent', () => {
  let component: CultrualOfferingTypeDetailsComponent;
  let fixture: ComponentFixture<CultrualOfferingTypeDetailsComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group').and.returnValue({value: {}})
  };
  const snackbarStub = jasmine.createSpy('snackbarStub');
  const culturalOfferingSubTypeServiceStub = {
    getAllByTypeId: function() { 
      return of([]);
    }
  };
  const imageServiceStub = {
    getById: function() {
      return of([]);
    },
    upload: function() {
      return of([]);
    }
  };
  const culturalOfferingTypeServiceStub = {
    insert: function() { 
      return of(null);
    },
    update: function() { 
      return of(null);
    },
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CultrualOfferingTypeDetailsComponent ],
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
        CulturalOfferingTypeRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: CulturalOfferingTypeService, useValue: culturalOfferingTypeServiceStub},
        {provide: CulturalOfferingSubtypeService, useValue: culturalOfferingSubTypeServiceStub},
        {provide: ImageService, useValue: imageServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatSnackBar, useValue: snackbarStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CultrualOfferingTypeDetailsComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
    component.culturalOfferingType = {id: 1};
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.culturalOfferingTypeService).toBeTruthy();
    expect(component.culturalOfferingSubTypeService).toBeTruthy();
    expect(component.imageService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should fetch image', async () => {
    const picByte = [];
    component.culturalOfferingType.imageId = 1;
    spyOn(component.imageService, 'getById').and.callFake(() => of({picByte}));
    spyOn(component, 'calculateFileButtonWidth').and.callFake(() => {});
    await component.fetchImage();
    expect(component.base64Data).toEqual(picByte);
    expect(component.retrievedImage).toBeTruthy();
  });

  it('should fetch cultural offering sub types', async () => {
    const array = [];
    spyOn(component.culturalOfferingSubTypeService, 'getAllByTypeId').and.callFake(() => of(array));
    await component.fetchCulturalOfferingSubTypes();
    expect(component.culturalOfferingSubtypes).toEqual(array);
  });

  it('should add new cultural offering sub type', () => {
    component.culturalOfferingSubtypes = [{id: 1, subTypeName: 'Existing'}];
    component.changeCulturalOfferingSubtypes({item: {id: null, subTypeName: 'New'}, listChangeEventType: ListChangeEventType.ADD});
    expect(component.culturalOfferingSubtypes.length).toBe(2);
  });

  it('should not add new cultural offering sub type becuase of the existing name', () => {
    component.culturalOfferingSubtypes = [{id: 1, subTypeName: 'Existing'}];
    component.changeCulturalOfferingSubtypes({item: {id: null, subTypeName: 'Existing'}, listChangeEventType: ListChangeEventType.ADD});
    expect(component.culturalOfferingSubtypes.length).toBe(1);
  });

  it('should remove cultural offering sub type ', () => {
    component.culturalOfferingSubtypes = [{id: 1, subTypeName: 'Existing'}];
    component.changeCulturalOfferingSubtypes({item: {id: null, subTypeName: 'Existing'}, listChangeEventType: ListChangeEventType.REMOVE});
    expect(component.culturalOfferingSubtypes.length).toBe(0);
  });

  it('should fail insert', async () => {
    const toInsert = {imageId: 1};
    spyOn(component, 'getUploadImagePromise').and.callFake(() => Promise.resolve({id:toInsert.imageId}));
    spyOn(component, 'getInsertCulturalOfferingTypePromises').and.callFake(() => Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.culturalOfferingType);
  });

  it('should succeed insert', async () => {
    const toInsert = {id: 1, imageId: 1};
    spyOn(component, 'getUploadImagePromise').and.callFake(() => Promise.resolve({id: toInsert.imageId}));
    spyOn(component, 'getInsertCulturalOfferingTypePromises').and.callFake(() => Promise.resolve(toInsert));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(toInsert);
  });

  it('should fail update', async () => {
    const toUpdate = {id: 1, imageId: 1};
    spyOn(component, 'getUploadImagePromise').and.callFake(() => Promise.resolve({id:toUpdate.imageId}));
    spyOn(component, 'getUpdateCulturalOfferingTypePromises').and.callFake(() => Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.culturalOfferingType);
  });

  it('should succeed update', async () => {
    const toUpdate = {id: 1, imageId: 1};
    spyOn(component, 'getUploadImagePromise').and.callFake(() => Promise.resolve({id:toUpdate.imageId}));
    spyOn(component, 'getUpdateCulturalOfferingTypePromises').and.callFake(() => Promise.resolve(toUpdate));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(toUpdate);
  });
});
