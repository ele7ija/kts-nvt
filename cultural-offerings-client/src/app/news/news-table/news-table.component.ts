import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';
import { CulturalOfferingService } from '../../core/services/cultural-offering/cultural-offering.service';
import { AbstractCrudService } from '../../core/model/abstract-crud-service';
import { TableComponent } from 'src/app/shared/modules/table/table/table.component';
import { CulturalOffering } from '../../core/model/cultural-offering';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CulturalOfferingTypeService } from '../../../app/core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingType } from '../../../app/core/model/cultural-offering-type';
import { NewsService } from 'src/app/core/services/news/news.service';
import { News } from 'src/app/core/model/news';

@Component({
  selector: 'app-news-table',
  templateUrl: './news-table.component.html',
  styleUrls: ['./news-table.component.scss'],
  providers: [
    {
      provide: AbstractCrudService,
      useClass: NewsService
    }
  ],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class NewsTableComponent extends TableComponent<News> {

  lastDeleted: News;

  constructor(
    private newsService: AbstractCrudService<News>,
    private matSnackbar: MatSnackBar
  )
  {
    super(newsService, matSnackbar);
    this.displayedColumns = [{field: 'id', text: 'ID'}, {field: 'title', text: 'Naslov'},
      {field: 'date', text: 'Datum kreiranja'}, {field: 'Actions', text: 'Akcije'}];
  }

  ngAfterViewInit(){
    super.ngAfterViewInit();
  }

  async delete(entity: News) {
    this.lastDeleted = entity;
    super.delete(entity)
    .then(() => {
      this.lastDeleted = null;
      this.showSnackbar('USPESNO BRISANJE', `Email sa nazivom ${entity.title} je uspesno obrisan.`, true)
    })
    .catch(error => {
      this.lastDeleted = null;
      this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false)
    });

  }

}
