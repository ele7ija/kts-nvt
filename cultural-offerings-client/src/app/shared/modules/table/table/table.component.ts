import { Component, AfterViewInit, ViewChild } from '@angular/core';
import {merge, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, MatSortable } from '@angular/material/sort';
import { Optional } from 'src/app/core/model/optional/optional';
import { PageableRequest } from 'src/app/core/model/pageable-request';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { TableColumnDefinition } from 'src/app/core/model/table-column-definition';
import { SimpleSnackbarComponent } from '../../../components/snackbar/simple-snackbar/simple-snackbar.component';

interface Identifiable{
  id: number;
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent<T extends Identifiable> implements AfterViewInit {
  data: T[];
  displayedColumns: TableColumnDefinition[];
  
  //pagination and sort logic
  fetchFailure: boolean = false;
  isLoading: boolean = true;
  totalLength: number = 0;

  //row expand logic
  expandedItem: Optional<T> = new Optional<T>();

  //is add form visible?
  addStarted: boolean = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    public apiService: AbstractCrudService<T>,
    public matSnackBar: MatSnackBar) {}

  ngAfterViewInit(){
    Promise.all([
      this.prepareTable(),
      this.fetchAditionalEntities()
    ]);
  }

  protected async prepareTable(): Promise<any>{
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
    });
    return merge(this.sort.sortChange, this.paginator.page)
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
          return this.apiService.getAll(pageableRequest);
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
      .subscribe(data => this.data = data);
  }

  protected async fetchAditionalEntities(): Promise<void>{
    //hook method
  }

  getHeadersField(): string[]{
    return this.displayedColumns.map(x => x.field);
  }

  toggleRow(element: T){
    if(this.expandedItem.value != element)
      this.expandedItem.value = element;
    else
      this.expandedItem.value = null;
    this.addStarted = false;
  }

  upsertLocal(event: T){
    const index = this.data.findIndex((item: T) => item.id == event.id);
    if(index == -1){
      //insert
      //this.culturalOfferingTypes.push(event); ne radimo ovo zbog paginacije
      this.addStarted = false;

      let sortable: MatSortable = this.sort.sortables.get(this.sort.active);
      this.sort.direction = this.sort.getNextSortDirection(sortable);
      this.sort.sort(sortable);
      
      this.totalLength += 1;
    }else{
      //update
      this.data.splice(index, 1, event);
      this.expandedItem.value = null;
    }
    this.data = [...this.data]; //for some reason angular does not detect changes on this array unles we do this
  }

  protected async delete(entity: T): Promise<any>{
    try{
      await this.apiService.delete(entity.id).toPromise();
      const index = this.data.findIndex((item: T) => item.id == entity.id);
      this.data.splice(index, 1);
      this.data = [...this.data]; //for some reason angular does not detect changes on this array unles we do this
      if(!this.paginator.hasNextPage() && this.totalLength % this.paginator.pageSize == 1){
        this.paginator.previousPage();
      }else{
        if(this.paginator.hasPreviousPage()){
          this.paginator.previousPage();
          this.paginator.nextPage();
        }else{
          this.paginator.nextPage();
          this.paginator.previousPage();
        }
      }
      this.totalLength -= 1;
      return Promise.resolve();
    }catch({error}){
      return Promise.reject(error);
    }
  }

  showSnackbar(title: string, message: string, success: boolean) {
    this.matSnackBar.openFromComponent(SimpleSnackbarComponent, {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000,
      data: {
        title,
        message,
        success,
      },

    });
  }
  

}
