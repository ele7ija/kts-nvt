import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GoogleMapsModule } from '@angular/google-maps'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CarouselModule, MDBBootstrapModule } from 'angular-bootstrap-md';

import { HomeRoutingModule } from './home-routing.module';
import { HomepageComponent } from './homepage/homepage.component';
import { CulturalOfferingTypeService } from '../core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingSubtypeService } from '../core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingService } from '../core/services/cultural-offering/cultural-offering.service';

import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ImageService } from '../core/services/image/image.service';

@NgModule({
  declarations: [HomepageComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    GoogleMapsModule,
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
    MatProgressSpinnerModule
  ],
  providers: [
    CulturalOfferingTypeService,
    CulturalOfferingSubtypeService,
    CulturalOfferingService
  ]
})
export class HomeModule { }
