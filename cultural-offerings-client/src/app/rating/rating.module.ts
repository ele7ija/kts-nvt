import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RatingListComponent } from './rating-list/rating-list.component';
import { RatingItemComponent } from './rating-item/rating-item.component';
import { PaginatorModule } from '../shared/modules/paginator/paginator.module';
import { RatingService } from '../core/services/rating/rating.service';


@NgModule({
  declarations: [RatingListComponent, RatingItemComponent],
  imports: [
    CommonModule,
    PaginatorModule
  ],
  providers: [
    RatingService
  ],
  exports: [RatingListComponent]
})
export class RatingModule { }
