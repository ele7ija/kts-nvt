import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { EventEmitter } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommentModule } from 'src/app/comment/comment.module';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { RatingsModule } from 'src/app/rating/rating.module';
import { CarouselWrapperModule } from 'src/app/shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from 'src/app/shared/modules/google-autocomplete/google-autocomplete.module';
import { TableModule } from 'src/app/shared/modules/table/table.module';
import { CulturalOfferingRoutingModule } from '../cultural-offering-routing.module';
import { CulturalOfferingTableComponent } from './cultural-offering-table.component';
import {of} from 'rxjs';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('CulturalOfferingTableComponent', () => {
  let component: CulturalOfferingTableComponent;
  let fixture: ComponentFixture<CulturalOfferingTableComponent>;

  const abStractCrudServiceStub = {
    getAll: jasmine.createSpy('getAll').and.returnValue(of({content: [{id: 1}, {id: 2}], totalElements: 2})),
    delete: jasmine.createSpy('delete').and.returnValue(of(true))
  };

  const culturalOfferingTypeServiceStub = {
    getAllEntities: jasmine.createSpy('getAllEntities').and.returnValue(of([{id: 1}, {id: 2}]))
  };

  const apiServiceStub =  jasmine.createSpy('apiServiceStub');

  const httpClientStub =  jasmine.createSpy('httpClientStub');

  const snackbarStub = {
    openFromComponent: jasmine.createSpy('delete')
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingTableComponent ],
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
        {provide: AbstractCrudService, useValue: abStractCrudServiceStub},
        {provide: CulturalOfferingTypeService, useValue: culturalOfferingTypeServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatSnackBar, useValue: snackbarStub},
        MatPaginator,
        MatSort,
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingTableComponent);
    component = fixture.componentInstance;
    (component as any).paginator = {
      page: new EventEmitter<PageEvent>(),
      nextPage: jasmine.createSpy('nextPage'),
      hasNextPage: jasmine.createSpy('hasNextPage').and.returnValue(false),
      previousPage: jasmine.createSpy('previousPage'),
      hasPreviousPage: jasmine.createSpy('hasPreviousPage').and.returnValue(false)
    };
    (component as any).sort = {
      sortChange: new EventEmitter<Sort>(),
      sortables: {
        get: jasmine.createSpy('get')
      },
      getNextSortDirection: jasmine.createSpy('getNextSortDirection'),
      sort: jasmine.createSpy('sort')
    };
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should define children', () => {
    expect(component.paginator).toBeTruthy();
    expect(component.sort).toBeTruthy();
  });

  it('should fetch additional entities', async () => {
    await component.fetchAditionalEntities();
    expect(component.culturalOfferingTypes.length).toBe(2);
  });
});
