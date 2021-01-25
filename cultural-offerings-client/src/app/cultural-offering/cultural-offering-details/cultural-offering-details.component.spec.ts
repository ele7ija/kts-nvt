import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { CommentModule } from 'src/app/comment/comment.module';
import { RatingsModule } from 'src/app/rating/rating.module';
import { CarouselWrapperModule } from 'src/app/shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from 'src/app/shared/modules/google-autocomplete/google-autocomplete.module';
import { CulturalOfferingRoutingModule } from '../cultural-offering-routing.module';
import { CulturalOfferingDetailsComponent } from './cultural-offering-details.component';
import {of} from 'rxjs';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingService } from 'src/app/core/services/cultural-offering/cultural-offering.service';
import { ImageService } from 'src/app/core/services/image/image.service';
import { CulturalOffering } from 'src/app/core/model/cultural-offering';

describe('CulturalOfferingDetailsComponent', () => {
  let component: CulturalOfferingDetailsComponent;
  let fixture: ComponentFixture<CulturalOfferingDetailsComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group').and.returnValue({
      value: {}, 
      controls: {
        culturalOfferingSubtypeName: {
          patchValue: () => {}
        }
      }
    })
  };
  const snackbarStub = jasmine.createSpy('snackbarStub');
  const culturalOfferingServiceStub = {
    insert: function() { 
      return of(null);
    },
    update: function() { 
      return of(null);
    },
  };
  const imageServiceStub = {
    getById: function() {
      return of([]);
    },
    upload: function() {
      return of([]);
    }
  };
  const culturalOfferingSubTypeServiceStub = {
    getAllByTypeId: function(){
      return of([]);
    }
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingDetailsComponent ],
      imports: [
        BrowserAnimationsModule,
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        TableModule,
        MatIconModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        CarouselWrapperModule,
        GoogleAutocompleteModule,
        CulturalOfferingRoutingModule,
        MatTabsModule,
        CommentModule,
        RatingsModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: CulturalOfferingService, useValue: culturalOfferingServiceStub},
        {provide: CulturalOfferingSubtypeService, useValue: culturalOfferingSubTypeServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: ImageService, useValue: imageServiceStub},
        {provide: MatSnackBar, useValue: snackbarStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingDetailsComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.culturalOfferingService).toBeTruthy();
    expect(component.culturalOfferingSubTypeService).toBeTruthy();
    expect(component.imageService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should fetch image', async () => {
    const picByte = [];
    (component.culturalOffering as any ) = {};
    component.culturalOffering.imageIds = [1,2];
    spyOn(component.imageService, 'getById').and.callFake(() => of({picByte}));
    await component.fetchImages();
    expect(component.imageModels.length).toBe(2);
    expect(component.imageModels.length).toBe(2);
  });

  it('should fetch cultural offering sub types', async () => {
    const temp = {id: 1, subTypeName: 'ST1'};
    component.culturalOfferingTypes = [{id: 1, typeName: 'T1'}, {id: 1, typeName: 'T2'}];
    spyOn(component.culturalOfferingSubTypeService, 'getAllByTypeId').and.callFake(() => of([temp]));
    spyOn(component.culturalOfferingForm.controls.culturalOfferingSubtypeName, 'patchValue');
    await component.fetchCulturalOfferingSubTypes('T1');
    expect(component.culturalOfferingForm.controls.culturalOfferingSubtypeName.patchValue).toHaveBeenCalledWith(temp.subTypeName);
  });

  it('should fail insert', async () => {
    const toInsert = {imageId: 1};
    spyOn(component, 'getUploadImagesPromise').and.callFake(() => Promise.resolve([{id:toInsert.imageId}]));
    spyOn(component, 'getInsertCulturalOfferingPromise').and.callFake(() => Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit')
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.culturalOffering);
  });

  it('should succeed insert', async () => {
    const toInsert: CulturalOffering = {id: 1, imageIds: [1], culturalOfferingSubtypeName: '', culturalOfferingTypeName: '', description: '', locationId: null, latitude: null, locationName: '', longitude: null, name: null};
    spyOn(component, 'getUploadImagesPromise').and.callFake(() => Promise.resolve([{id:toInsert.imageIds[0]}]));
    spyOn(component, 'getInsertCulturalOfferingPromise').and.callFake(() => Promise.resolve(toInsert));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(toInsert);
  });

  it('should fail update', async () => {
    const toUpdate = {id: 1, imageId: 1};
    spyOn(component, 'getUploadImagesPromise').and.callFake(() => Promise.resolve([{id:toUpdate.imageId}]));
    spyOn(component, 'getUpdateCulturalOfferingPromise').and.callFake(() => Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.culturalOffering);
  });

  it('should succeed update', async () => {
    const toUpdate: CulturalOffering = {id: 1, imageIds: [1], culturalOfferingSubtypeName: '', culturalOfferingTypeName: '', description: '', locationId: null, latitude: null, locationName: '', longitude: null, name: null};
    spyOn(component, 'getUploadImagesPromise').and.callFake(() => Promise.resolve([{id:toUpdate.imageIds[0]}]));
    spyOn(component, 'getUpdateCulturalOfferingPromise').and.callFake(() => Promise.resolve(toUpdate));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(toUpdate);
  });
});
