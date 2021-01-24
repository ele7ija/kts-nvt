import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommentModule } from 'src/app/comment/comment.module';
import { RatingsModule } from 'src/app/rating/rating.module';
import { CarouselWrapperModule } from 'src/app/shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from 'src/app/shared/modules/google-autocomplete/google-autocomplete.module';
import { TableModule } from 'src/app/shared/modules/table/table.module';
import { CulturalOfferingRoutingModule } from '../cultural-offering-routing.module';
import { CulturalOfferingPageComponent } from './cultural-offering-page.component';
import {of} from 'rxjs';
import { CulturalOfferingService } from 'src/app/core/services/cultural-offering/cultural-offering.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { ImageService } from 'src/app/core/services/image/image.service';
import { MatSnackBar } from '@angular/material/snack-bar';

describe('CulturalOfferingPageComponent', () => {
  let component: CulturalOfferingPageComponent;
  let fixture: ComponentFixture<CulturalOfferingPageComponent>;
  
  const snackbarStub = jasmine.createSpy('snackbarStub');
  const culturalOfferingServiceStub = {
    getOne: function(){
      return of({imageIds: []})
    },
    insert: function() { 
      return of(null);
    },
    update: function() { 
      return of(null);
    },
  };
  const imageServiceStub = {
    getById: function() {
      return of({});
    },
    upload: function() {
      return of({});
    }
  };
  
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');
  const activatedRouteStub = {
    paramMap: {
      subscribe: jasmine.createSpy('paramMap').and.returnValue(of({}))
    }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingPageComponent ],
      imports: [
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
        {provide: CulturalOfferingService, useValue: culturalOfferingServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: ImageService, useValue: imageServiceStub},
        {provide: MatSnackBar, useValue: snackbarStub},
        {provide: ActivatedRoute, useValue: activatedRouteStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingPageComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.culturalOfferingService).toBeTruthy();
    expect(component.imageService).toBeTruthy();
    expect(component.matSnackbar).toBeTruthy();
  });

  it('should fetch images', async () => {
    component.culturalOffering = {id: 1, imageIds: [1,2,3], culturalOfferingSubtypeName: '', culturalOfferingTypeName: '', description: '', locationId: null, latitude: null, locationName: '', longitude: null, name: null};
    await component.fetchImages();
    expect(component.images.length).toBe(3);
  });
});
