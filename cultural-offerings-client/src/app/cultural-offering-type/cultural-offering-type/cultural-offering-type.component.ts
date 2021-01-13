import { Component, AfterViewInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { CulturalOfferingTypeService } from '../../core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingType } from '../../core/model/cultural-offering-type';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TableComponent } from 'src/app/shared/modules/table/table/table.component';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';

@Component({
  selector: 'app-cultural-offering-type',
  templateUrl: './cultural-offering-type.component.html',
  styleUrls: ['./cultural-offering-type.component.scss'],
  providers: [
    { 
      provide: AbstractCrudService, 
      useClass: CulturalOfferingTypeService 
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
export class CulturalOfferingTypeComponent extends TableComponent<CulturalOfferingType> {

  constructor(
    public culturalOfferingTypeService: AbstractCrudService<CulturalOfferingType>,
    public matSnackBar: MatSnackBar
  ){ 
    super(culturalOfferingTypeService, matSnackBar);
    this.displayedColumns = [{field: 'id', text: 'ID'}, {field: 'typeName', text: 'Naziv'}, {field: 'Actions', text: 'Akcije'}];
  }

  ngAfterViewInit(){
    super.ngAfterViewInit();
  }
  
  async delete(entity: CulturalOfferingType) {
    super.delete(entity)
      .then(() => this.showSnackbar('USPESNO BRISANJE', `Tip kategorije pod nazivom ${entity.typeName} je uspesno obrisan.`, true))
      .catch(error => this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false));
  }
}
