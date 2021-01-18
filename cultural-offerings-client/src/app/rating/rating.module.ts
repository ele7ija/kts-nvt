import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RatingListComponent } from './rating-list/rating-list.component';
import { RatingItemComponent } from './rating-item/rating-item.component';
import { PaginatorModule } from '../shared/modules/paginator/paginator.module';
import { RatingService } from '../core/services/rating/rating.service';
import { RatingModule } from 'ng-starrating';
import { MatIconModule } from '@angular/material/icon';
import { RatingInputComponent } from './rating-input/rating-input.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [RatingListComponent, RatingItemComponent, RatingInputComponent],
  imports: [
    CommonModule,
    PaginatorModule,
    RatingModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  providers: [
    RatingService
  ],
  exports: [RatingListComponent]
})
export class RatingsModule { }
