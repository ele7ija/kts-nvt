import { Component, AfterViewInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { CulturalOfferingSubtype } from 'src/app/core/model/cultural-offering-subtype';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TableComponent } from 'src/app/shared/modules/table/table/table.component';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';

@Component({
  selector: 'app-cultural-offering-sub-type',
  templateUrl: './cultural-offering-sub-type.component.html',
  styleUrls: ['./cultural-offering-sub-type.component.scss'],
  providers: [
    { 
      provide: AbstractCrudService, 
      useClass: CulturalOfferingSubtypeService 
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
export class CulturalOfferingSubTypeComponent extends TableComponent<CulturalOfferingSubtype> implements AfterViewInit {

  constructor(
    public culturalOfferingSubtypeService: AbstractCrudService<CulturalOfferingSubtype>,
    public matSnackBar: MatSnackBar) {
      super(culturalOfferingSubtypeService, matSnackBar);
      this.displayedColumns = [{field: 'id', text: 'ID'}, {field: 'subTypeName', text: 'Naziv podkategorije'}, {field: 'Actions', text: 'Akcije'}];
  }

  ngAfterViewInit(){
    super.ngAfterViewInit();
  }

  async delete(entity: CulturalOfferingSubtype) {
    super.delete(entity)
      .then(() => this.showSnackbar('USPESNO BRISANJE', `Podtip kategorije pod nazivom ${entity.subTypeName} je uspesno obrisan.`, true))
      .catch(error => this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false));
  }


}
