import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { CulturalOfferingService } from '../../core/services/cultural-offering/cultural-offering.service';
import { AbstractCrudService } from '../../core/model/abstract-crud-service';
import { TableComponent } from 'src/app/shared/modules/table/table/table.component';
import { CulturalOffering } from '../../core/model/cultural-offering';
import { MatSnackBar } from '@angular/material/snack-bar';

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

  constructor(
    private culturalOfferingService: CulturalOfferingService,
    private matSnackbar: MatSnackBar
  ) 
  { 
    super(culturalOfferingService, matSnackbar);
    this.displayedColumns = [{field: 'id', text: 'ID'}, {field: 'name', text: 'Naziv'}, {field: 'Actions', text: 'Akcije'}];
  }

  ngAfterViewInit(){
    super.ngAfterViewInit();
  }

  async delete(entity: CulturalOffering) {
    super.delete(entity)
      .then(() => this.showSnackbar('USPESNO BRISANJE', `Tip kategorije pod nazivom ${entity.name} je uspesno obrisan.`, true))
      .catch(error => this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false));
  }

  openNews(entity: CulturalOffering){
    console.log("Should open new page");
  }

}
