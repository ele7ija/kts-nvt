import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { EventEmitter } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, Sort } from '@angular/material/sort';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { SuperAdminRoutingModule } from '../super-admin-routing.module';
import {of} from 'rxjs';
import { UserTableComponent } from './user-table.component';

describe('UserTableComponent', () => {
  let component: UserTableComponent;
  let fixture: ComponentFixture<UserTableComponent>;

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
      declarations: [ UserTableComponent ],
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
        
        SuperAdminRoutingModule
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
    fixture = TestBed.createComponent(UserTableComponent);
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
