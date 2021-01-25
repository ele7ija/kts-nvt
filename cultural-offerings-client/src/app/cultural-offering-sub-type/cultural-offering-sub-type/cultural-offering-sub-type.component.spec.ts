import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingSubTypeRoutingModule } from '../cultural-offering-sub-type-routing.module';
import { MatSort, Sort } from '@angular/material/sort';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { CulturalOfferingSubTypeComponent } from './cultural-offering-sub-type.component';
import { EventEmitter } from '@angular/core';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import {of} from 'rxjs';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

describe('CulturalOfferingSubTypeComponent', () => {
  let component: CulturalOfferingSubTypeComponent;
  let fixture: ComponentFixture<CulturalOfferingSubTypeComponent>;

  const abStractCrudServiceStub = {
    getAll: jasmine.createSpy('getAll').and.returnValue(of({content: [{id: 1}, {id: 2}], totalElements: 2})),
    delete: jasmine.createSpy('delete').and.returnValue(of(true))
  };

  const culturalOfferingTypeServiceStub = {
    getAllEntities: jasmine.createSpy('getAllEntities').and.returnValue(of([{id: 1}, {id: 2}]))
  };

  const apiServiceStub = jasmine.createSpy('apiServiceStub');

  const httpClientStub = jasmine.createSpy('httpClientStub');

  const snackbarStub = {
    openFromComponent: jasmine.createSpy('openFromComponent')
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingSubTypeComponent ],
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
        {
          provide: AbstractCrudService,
          useValue: abStractCrudServiceStub,
        },
        {
          provide: CulturalOfferingTypeService,
          useValue: culturalOfferingTypeServiceStub,
        },
        {
          provide: ApiService,
          useValue: apiServiceStub
        },
        {
          provide: HttpClient,
          useValue: httpClientStub
        },
        {
          provide: MatSnackBar,
          useValue: snackbarStub
        },
        MatPaginator,
        MatSort
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferingSubTypeComponent);
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
