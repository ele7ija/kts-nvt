import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSort, MatSortModule, Sort } from '@angular/material/sort';
import { User } from 'src/app/core/model/user';
import {of} from 'rxjs';

import { TableComponent } from './table.component';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { EventEmitter } from '@angular/core';

describe('TableComponent', () => {
  let component: TableComponent<User>;
  let fixture: ComponentFixture<TableComponent<User>>;
  
  const snackbarStub = {
    openFromComponent: jasmine.createSpy('delete')
  };

  beforeEach(async () => {
    const apiServiceStub = {
      getAll: jasmine.createSpy('getAll').and.returnValue(of({content: [{id: 1}, {id: 2}], totalElements: 2})),
      delete: jasmine.createSpy('delete').and.returnValue(of(true))
    };

    await TestBed.configureTestingModule({
      declarations: [ TableComponent ],
      imports: [
        CommonModule,
        MatPaginatorModule,
        MatSortModule,
        MatSnackBarModule
      ],
      providers: [
        {provide: AbstractCrudService, useValue: apiServiceStub},
        {provide: MatSnackBar, useValue: snackbarStub},
        MatPaginator,
        MatSort,
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent<TableComponent<User>>(TableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
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

  it('should set data attribute', async () => {
    //mockovanje ViewChild preko Jasmine spy ne radi
    await component.prepareTable();
    expect(component.isLoading).toBeFalsy();
    expect(component.fetchFailure).toBeFalsy();
    expect(component.totalLength).toBe(2);
    expect(component.data.length).toBe(2); 
  });

  it('should update element from data array', async () => {
    await component.prepareTable();
    component.upsertLocal({id: 1, firstName: "Ime", lastName: "Prezime", email: "Email"});
    expect(component.data[0].firstName).toBe("Ime");
    expect(component.data[0].lastName).toBe("Prezime");
    expect(component.data[0].email).toBe("Email");
  });

  it('should increase total length', async () => {
    await component.prepareTable();
    const totalLength = component.totalLength;
    component.upsertLocal({id: 15, firstName: "Ime", lastName: "Prezime", email: "Email"});
    expect(component.totalLength).toBe(totalLength + 1);
  });

  it('should delete', async () => {
    await component.prepareTable();
    const totalLength = component.totalLength;
    expect(component.data.find(element => element.id == 1)).toBeTruthy();
    await component.delete({id: 1, firstName: "Ime", lastName: "Prezime", email: "Email"});
    expect(component.totalLength).toBe(totalLength - 1);
    expect(component.data.find(element => element.id == 1)).toBeFalsy();
  });

  it('should not delete', async () => {
    await component.prepareTable();
    const totalLength = component.totalLength;
    expect(component.data.find(element => element.id == 1)).toBeTruthy();
    expect(await component.delete({id: 12, firstName: "Ime", lastName: "Prezime", email: "Email"})).toBeFalsy();
    expect(component.data.find(element => element.id == 1)).toBeTruthy();
  });
});
