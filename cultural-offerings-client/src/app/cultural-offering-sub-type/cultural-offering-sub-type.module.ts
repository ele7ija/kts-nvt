import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CulturalOfferingSubTypeRoutingModule } from './cultural-offering-sub-type-routing.module';
import { CulturalOfferingSubTypeComponent } from './cultural-offering-sub-type/cultural-offering-sub-type.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { TableModule } from '../shared/modules/table/table.module';
import { CulturalOfferingSubTypeDetailsComponent } from './cultural-offering-sub-type-details/cultural-offering-sub-type-details.component';
import { CulturalOfferingTypeService } from '../core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingSubtypeService } from '../core/services/cultural-offering-subtype/cultural-offering-subtype.service';

@NgModule({
  declarations: [
    CulturalOfferingSubTypeComponent,
    CulturalOfferingSubTypeDetailsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),

    MatIconModule,
    MatChipsModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,

    TableModule,

    CulturalOfferingSubTypeRoutingModule
  ],
  providers: [
    CulturalOfferingTypeService,
    CulturalOfferingSubtypeService
  ]
})
export class CulturalOfferingSubTypeModule { }
