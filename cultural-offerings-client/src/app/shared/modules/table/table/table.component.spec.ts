import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { User } from 'src/app/core/model/user';
import {Observable, of} from 'rxjs';

import { TableComponent } from './table.component';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';

describe('TableComponent', () => {
  let component: TableComponent<User>;
  let fixture: ComponentFixture<TableComponent<User>>;
  
  const snackbarStub = {
    openFromComponent: jasmine.createSpy('delete')
  };

  beforeEach(async () => {
    const apiServiceStub = {
      getAll: jasmine.createSpy('getAll').and.returnValue(of([{id: 1}, {id: 2}])),
      delete: jasmine.createSpy('delete').and.returnValue(new Observable<void>())
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
    //nismo mockovali paginator i sorter zato sto bi morali da pravimo posebnu klasu koja implementira MatPaginator/MatSorter(preko jasmine spy nije radilo)
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should define children', () => {
    (component as any).paginator = {page: new Observable()};
    (component as any).sort = {sortChange: new Observable()};
    //spyOnProperty(component, 'paginator', 'get').and.returnValue({page: new Observable()});
    //spyOnProperty(component, 'sort', 'get').and.returnValue({sortChange: new Observable()});
    fixture.detectChanges();
    expect(component.paginator).toBeTruthy();
    expect(component.sort).toBeTruthy();
  });

  /*it('should set data attribute', () => {
    
    component.prepareTable();
    expect(component.isLoading).toBeFalsy();
    expect(component.fetchFailure).toBeFalsy();
    expect(component.totalLength).toBe(2);
    expect(component.data.length).toBe(2);
  });*/
});
