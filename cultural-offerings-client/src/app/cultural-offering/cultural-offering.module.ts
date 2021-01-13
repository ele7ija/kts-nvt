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

@NgModule({
  declarations: [
    CulturalOfferingTableComponent,
    CulturalOfferingDetailsComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    MatSnackBarModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatFormFieldModule,
    MatProgressSpinnerModule
  ],
  exports: [
    CulturalOfferingTableComponent
  ]
})
export class CulturalOfferingModule { }
