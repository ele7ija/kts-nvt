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
import { AuthService } from '../core/services/security/auth-service/auth.service';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [RatingListComponent, RatingItemComponent, RatingInputComponent],
  imports: [
    CommonModule,
    RouterModule,
    PaginatorModule,
    RatingModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  providers: [
    RatingService,
    AuthService
  ],
  exports: [RatingListComponent]
})
export class RatingsModule { }
