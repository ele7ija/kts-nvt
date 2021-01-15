import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CulturalOfferingTableComponent } from './cultural-offering-table/cultural-offering-table.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CulturalOfferingDetailsComponent } from './cultural-offering-details/cultural-offering-details.component';
import { CarouselWrapperModule } from '../shared/modules/carousel/carousel.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CulturalOfferingService } from '../core/services/cultural-offering/cultural-offering.service';
import { CulturalOfferingSubtypeService } from '../core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from '../core/services/cultural-offering-type/cultural-offering-type.service';
import { ImageService } from '../core/services/image/image.service';
import { GoogleAutocompleteModule } from '../shared/modules/google-autocomplete/google-autocomplete.module';

@NgModule({
  declarations: [
    CulturalOfferingTableComponent,
    CulturalOfferingDetailsComponent
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
    MatFormFieldModule,
    MatProgressSpinnerModule,
    CarouselWrapperModule,
    GoogleAutocompleteModule
  ],
  providers: [
    CulturalOfferingService,
    CulturalOfferingTypeService,
    CulturalOfferingSubtypeService,
    ImageService
  ],
  exports: [
    CulturalOfferingTableComponent
  ]
})
export class CulturalOfferingModule { }
