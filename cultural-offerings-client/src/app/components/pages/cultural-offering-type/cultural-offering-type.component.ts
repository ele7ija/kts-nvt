import { Component, ViewChild, AfterViewInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {merge, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { CulturalOfferingTypeService } from '../../../services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingType } from '../../../model/cultural-offering-type/cultural-offering-type';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PageableRequest } from 'src/app/model/pageable-request/pageable-request';
import { Optional } from 'src/app/model/optional/optional';
import { SimpleSnackbarComponent } from '../../snackbar/simple-snackbar/simple-snackbar.component';

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
  displayedColumns: any[] = [{field: 'id', text: 'ID'}, {field: 'typeName', text: 'Naziv'}, {field: 'Actions', text: 'Akcije'}];
  
  //pagination and sort logic
  fetchFailure: boolean = false;
  isLoading: boolean = true;
  totalLength: number = 0;

  //row expand logic
  expandedItem: Optional<CulturalOfferingType> = new Optional<CulturalOfferingType>();

  //is add form visible?
  addStarted: boolean = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private culturalOfferingTypeService: CulturalOfferingTypeService,
    private matSnackBar: MatSnackBar) { }

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
    this.addStarted = false;
  }

  upsertLocal(event: CulturalOfferingType){
    const index = this.culturalOfferingTypes.findIndex((item: CulturalOfferingType) => item.id == event.id);
    if(index == -1){
      //insert
      //this.culturalOfferingTypes.push(event); ne radimo ovo zbog paginacije
      this.addStarted = false;
      this.paginator.firstPage(); //za sada ovako
      this.totalLength += 1;
    }else{
      //update
      this.culturalOfferingTypes.splice(index, 1, event);
      this.expandedItem.value = null;
    }
    this.culturalOfferingTypes = [...this.culturalOfferingTypes]; //for some reason angular does not detect changes on this array unles we do this
  }

  async delete(entity: CulturalOfferingType){
    try{
      await this.culturalOfferingTypeService.delete(entity.id).toPromise();
      const index = this.culturalOfferingTypes.findIndex((item: CulturalOfferingType) => item.id == entity.id);
      this.culturalOfferingTypes.splice(index, 1);
      this.culturalOfferingTypes = [...this.culturalOfferingTypes]; //for some reason angular does not detect changes on this array unles we do this
      if(!this.paginator.hasNextPage() && this.totalLength % this.paginator.pageSize == 0){
        this.paginator.previousPage();
      }else{
        this.paginator.nextPage();
        this.paginator.previousPage();
      }
      this.totalLength -= 1;
      this.showSnackbar('USPESNO BRISANJE', `Tip kategorije pod nazivom ${entity.typeName} je uspesno obrisan.`, true);
    }catch({error}){
      this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false);
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
