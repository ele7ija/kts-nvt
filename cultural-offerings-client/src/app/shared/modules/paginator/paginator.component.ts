import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { PageableRequest } from 'src/app/core/model/pageable-request';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss']
})
export class PaginatorComponent implements OnInit {

  @Input()
  totalLength: number = 0;

  @Input()
  pageIndex: number = 0;

  @Input()
  pageSizeOptions: number[] = [3,5,10,20];

  pageSize: number = 0;
  
  @Output()
  pageEvent: EventEmitter<PageableRequest> = new EventEmitter<PageableRequest>();

  constructor() { }

  ngOnInit(): void {
    this.pageSize = this.pageSizeOptions[0];
  }

  onPageEvent(pageEvent: PageEvent){
    //jedino cime paginator sam upravlja je pageSize, sve ostalo dobija od roditeljske komponente
    this.pageSize = pageEvent.pageSize;
    this.pageEvent.emit({
      page: pageEvent.pageIndex,
      size: pageEvent.pageSize
    });
  }

}
