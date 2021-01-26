import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatPaginatorModule } from '@angular/material/paginator';
import { By } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { PaginatorComponent } from './paginator.component';

describe('PaginatorComponent', () => {
  let component: PaginatorComponent;
  let fixture: ComponentFixture<PaginatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaginatorComponent ],
      imports: [
        CommonModule,
        MatPaginatorModule,
        BrowserAnimationsModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaginatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should bind data to mat-paginator', () => {
    // neka vrsta polu unit, polu integracionog testa
    const matPaginatorComponent = fixture.debugElement.query(By.css('mat-paginator')).componentInstance;
    expect(matPaginatorComponent.length).toBe(component.totalLength);
    expect(matPaginatorComponent.pageSizeOptions).toEqual(component.pageSizeOptions);
    expect(matPaginatorComponent.pageIndex).toBe(component.pageIndex);
    expect(matPaginatorComponent.pageSize).toBe(component.pageSize);
  });

  it('should update page size and emit page event', () => {
    spyOn(component.pageEvent, 'emit');
    component.onPageEvent({pageIndex: 1, length: 10, pageSize: 5, previousPageIndex: 0});
    expect(component.pageSize).toBe(5);
    expect(component.pageEvent.emit).toHaveBeenCalled();
  });
});
