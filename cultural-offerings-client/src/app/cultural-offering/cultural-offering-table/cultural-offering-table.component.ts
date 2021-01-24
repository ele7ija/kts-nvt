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

@Component({
  selector: 'app-cultural-offering-table',
  templateUrl: './cultural-offering-table.component.html',
  styleUrls: ['./cultural-offering-table.component.scss'],
  providers: [
    { 
      provide: AbstractCrudService, 
      useClass: CulturalOfferingService
    }
  ],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class CulturalOfferingTableComponent extends TableComponent<CulturalOffering> {

  culturalOfferingTypes: CulturalOfferingType[];
  lastDeleted: CulturalOffering;

  constructor(
    private culturalOfferingService: AbstractCrudService<CulturalOffering>,
    private matSnackbar: MatSnackBar,
    private culturalOfferingTypeService: CulturalOfferingTypeService,
    private newsService: NewsService
  ) 
  { 
    super(culturalOfferingService, matSnackbar);
    this.displayedColumns = [{field: 'id', text: 'ID'}, {field: 'name', text: 'Naziv'}, {field: 'Actions', text: 'Akcije'}];
  }

  ngAfterViewInit(){
    super.ngAfterViewInit();
  }

  async delete(entity: CulturalOffering) {
    this.lastDeleted = entity;
    super.delete(entity)
    .then(() => {
      this.lastDeleted = null;
      this.showSnackbar('USPESNO BRISANJE', `Kulturna pomnuda pod nazivom ${entity.name} je uspesno obrisana.`, true)
    })
    .catch(error => {
      this.lastDeleted = null;
      this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false)
    });
    
  }

  async fetchAditionalEntities(): Promise<void>{
    this.culturalOfferingTypes = await this.culturalOfferingTypeService.getAllEntities().toPromise();
  }

  setSelected(element: CulturalOffering) : void {
    this.newsService.setSelectedOfferingId(element.id);
  }

}
