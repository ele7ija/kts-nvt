import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { CulturalOfferingTypeRoutingModule } from '../cultural-offering-type-routing.module';
import { CulturalOfferingTypeComponent } from './cultural-offering-type.component';
import {of} from 'rxjs';
import { MatSort, Sort } from '@angular/material/sort';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { EventEmitter } from '@angular/core';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';

describe('CulturalOfferingTypeComponent', () => {
  let component: CulturalOfferingTypeComponent;
  let fixture: ComponentFixture<CulturalOfferingTypeComponent>;

  const abStractCrudServiceStub = {
    getAll: jasmine.createSpy('getAll').and.returnValue(of({content: [{id: 1}, {id: 2}], totalElements: 2})),
    delete: jasmine.createSpy('delete').and.returnValue(of(true))
  };

  const apiServiceStub =  jasmine.createSpy('apiServiceStub');

  const httpClientStub =  jasmine.createSpy('httpClientStub');

  const snackbarStub = {
    openFromComponent: jasmine.createSpy('delete')
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferingTypeComponent ],
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
        {provide: AbstractCrudService, useValue: abStractCrudServiceStub},
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
    fixture = TestBed.createComponent(CulturalOfferingTypeComponent);
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
});
