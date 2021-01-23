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
import { CulturalOfferingPageComponent } from './cultural-offering-page/cultural-offering-page.component';
import { RouterModule } from '@angular/router';
import { CulturalOfferingRoutingModule } from './cultural-offering-routing.module';
import { CommentModule } from '../comment/comment.module';
import { RatingsModule } from '../rating/rating.module';
import { MatTabsModule } from '@angular/material/tabs';
import { NewsService } from '../core/services/news/news.service';

@NgModule({
  declarations: [
    CulturalOfferingTableComponent,
    CulturalOfferingDetailsComponent,
    CulturalOfferingPageComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    MatSnackBarModule,
    MatTableModule,
    MatPaginatorModule, //ovo se koristi za tabelu jer MatTable vec ima odradjenu integraciju sa MatPaginator
    MatSortModule,
    MatIconModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    CarouselWrapperModule,
    GoogleAutocompleteModule,
    CulturalOfferingRoutingModule,
    MatTabsModule,
    CommentModule,
    RatingsModule
  ],
  providers: [
    CulturalOfferingService,
    CulturalOfferingTypeService,
    CulturalOfferingSubtypeService,
    ImageService,
    NewsService
  ],
  exports: [
    CulturalOfferingTableComponent
  ]
})
export class CulturalOfferingModule { }
