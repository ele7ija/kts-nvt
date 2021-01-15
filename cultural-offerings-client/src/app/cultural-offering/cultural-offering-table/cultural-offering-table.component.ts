import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';
import { CulturalOfferingService } from '../../core/services/cultural-offering/cultural-offering.service';
import { AbstractCrudService } from '../../core/model/abstract-crud-service';
import { TableComponent } from 'src/app/shared/modules/table/table/table.component';
import { CulturalOffering } from '../../core/model/cultural-offering';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CulturalOfferingTypeService } from '../../../app/core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingSubtypeService } from '../../../app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingType } from '../../../app/core/model/cultural-offering-type';
import { CulturalOfferingSubtype } from '../../../app/core/model/cultural-offering-subtype';

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

  constructor(
    private culturalOfferingService: CulturalOfferingService,
    private matSnackbar: MatSnackBar,
    private culturalOfferingTypeService: CulturalOfferingTypeService
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

  async fetchAditionalEntities(): Promise<void>{
    this.culturalOfferingTypes = await this.culturalOfferingTypeService.getAllEntities().toPromise();
  }

}
