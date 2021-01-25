import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsDetailsComponent } from './news-details/news-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CarouselWrapperModule } from '../shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from '../shared/modules/google-autocomplete/google-autocomplete.module';
import { CulturalOfferingRoutingModule } from '../cultural-offering/cultural-offering-routing.module';
import { MatTabsModule } from '@angular/material/tabs';
import { NewsService } from '../core/services/news/news.service';
import { CulturalOfferingService } from '../core/services/cultural-offering/cultural-offering.service';
import { ImageService } from '../core/services/image/image.service';
import { NewsTableComponent } from './news-table/news-table.component';
import { NewsRoutingModule } from './news-routing.module';



@NgModule({
  declarations: [NewsDetailsComponent, NewsTableComponent],
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
    GoogleAutocompleteModule,
    CulturalOfferingRoutingModule,
    MatTabsModule,
    NewsRoutingModule
  ],
  providers: [
    NewsService,
    CulturalOfferingService,
    ImageService
  ],
  exports: [

  ]
})
export class NewsModule { }
