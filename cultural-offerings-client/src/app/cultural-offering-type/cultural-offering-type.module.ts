import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { CultrualOfferingTypeDetailsComponent } from './cultrual-offering-type-details/cultrual-offering-type-details.component';
import { CulturalOfferingSubtypeChipsComponent } from './cultural-offering-subtype-chips/cultural-offering-subtype-chips.component';
import { ImageService } from '../core/services/image/image.service';
import { CulturalOfferingTypeService } from '../core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingSubtypeService } from '../core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { SimpleSnackbarComponent } from '../shared/components/snackbar/simple-snackbar/simple-snackbar.component';
import { CulturalOfferingTypeComponent } from './cultural-offering-type/cultural-offering-type.component';
import { CulturalOfferingTypeRoutingModule } from './cultural-offering-type-routing.module';

@NgModule({
  declarations: [
    CulturalOfferingTypeComponent,
    CultrualOfferingTypeDetailsComponent,
    CulturalOfferingSubtypeChipsComponent,
    SimpleSnackbarComponent
  ],
  providers: [
    ImageService,
    CulturalOfferingSubtypeService,
    CulturalOfferingTypeService,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    MatSnackBarModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatChipsModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,

    CulturalOfferingTypeRoutingModule
  ],
  exports: [
    CulturalOfferingTypeComponent
  ]
})
export class CulturalOfferingTypeModule { }
