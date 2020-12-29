import { Component, ViewChild, AfterViewInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {merge, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { CulturalOfferingTypeService } from '../../../services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingType } from '../../../model/cultural-offering-type/cultural-offering-type';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { PageableRequest } from 'src/app/model/pageable-request/pageable-request';
import { Optional } from 'src/app/model/optional/optional';

@Component({
  selector: 'app-cultural-offering-type',
  templateUrl: './cultural-offering-type.component.html',
  styleUrls: ['./cultural-offering-type.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class CulturalOfferingTypeComponent implements AfterViewInit {
  //table headers and data
  culturalOfferingTypes: CulturalOfferingType[] = [];
  displayedColumns: any[] = [{field: 'id', text: 'ID'}, {field: 'typeName', text: 'Type Name'}, {field: 'Actions', text: 'Actions'}];
  
  //pagination and sort logic
  fetchFailure: boolean = false;
  isLoading: boolean = true;
  totalLength: number = 0;

  //row expand logic
  expandedItem: Optional<CulturalOfferingType> = new Optional<CulturalOfferingType>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private culturalOfferingTypeService: CulturalOfferingTypeService) { }

  ngAfterViewInit(){
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoading = true;
          const pageableRequest: PageableRequest = {
            page: this.paginator.pageIndex,
            size: this.paginator.pageSize,
            sort: this.sort.active,
            sortOrder: this.sort.direction ? this.sort.direction : 'asc'
          }
          return this.culturalOfferingTypeService.getAll(pageableRequest);
        }),
        map(data => {
          this.isLoading = false;
          this.fetchFailure = false;
          this.totalLength = data.totalElements;
          return data.content;
        }),
        catchError(() => {
          this.isLoading = false;
          this.fetchFailure = true;
          return observableOf([]);
        })
      )
      .subscribe(data => this.culturalOfferingTypes = data);
  }

  getHeadersField(): string[]{
    return this.displayedColumns.map(x => x.field);
  }

  toggleRow(element: CulturalOfferingType){
    if(this.expandedItem.value != element)
      this.expandedItem.value = element;
    else
      this.expandedItem.value = null;
  }
}
