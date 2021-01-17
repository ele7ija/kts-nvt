import { Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { PageableRequest } from 'src/app/core/model/pageable-request';
import { RatingInput } from '../../../app/core/model/rating-input';
import { PaginatorComponent } from 'src/app/shared/modules/paginator/paginator.component';
import { Rating } from '../../../app/core/model/rating';
import { RatingService } from '../../../app/core/services/rating/rating.service';
import { AuthService } from '../../../app/core/services/security/auth-service/auth.service';

@Component({
  selector: 'app-rating-list',
  templateUrl: './rating-list.component.html',
  styleUrls: ['./rating-list.component.scss']
})
export class RatingListComponent implements OnChanges {

  @Input()
  culturalOfferingId: number;

  ratings: Rating[] = [];
  totalLength: number;
  loading: boolean = false;
  pageSizeOptions: number[] = [5,10,20];
  pageIndex: number = 0;

  @ViewChild(PaginatorComponent) paginator: PaginatorComponent;

  constructor(
    private ratingService: RatingService,
    private authService: AuthService,
  ) { }

  ngOnChanges(changes: SimpleChanges) {
    if(changes.culturalOfferingId.currentValue){
      this.fetchRatings({page: 0, size: this.pageSizeOptions[0]});  
    }
  }

  async fetchRatings(pageableRequest: PageableRequest){
    this.loading = true;
    this.pageIndex = pageableRequest.page;
    pageableRequest.sort = 'date';
    pageableRequest.sortOrder = 'desc';
    if(this.culturalOfferingId){
      try{
        const pageableResponse = await this.ratingService.getAllByCulturalOfferingId(pageableRequest, this.culturalOfferingId).toPromise();
        this.ratings = pageableResponse.content;
        this.totalLength = pageableResponse.totalElements;
      }catch{
  
      }
    }
    this.loading = false;
  }

  async ratingAddedEvent(ratingInput: RatingInput){
    try{
      const newRating = await this.ratingService.insert({value: ratingInput.value, date: ratingInput.date, culturalOfferingId: this.culturalOfferingId, userId: this.authService.getUserId()}).toPromise();
      if(this.pageIndex == 0){
        this.ratings.unshift(newRating);
        if(this.ratings.length > this.paginator.pageSize)
          this.ratings.splice(this.ratings.length - 1, 1);
        this.totalLength += 1;
      }else{
        this.pageIndex = 0;
        this.fetchRatings({page: this.pageIndex, size: this.paginator.pageSize});
      }
    }catch(error){

    }
  }

  async removeRatingEvent(rating: Rating){
    await this.ratingService.delete(rating.id).toPromise();
    if(this.totalLength % this.paginator.pageSize == 1 && Math.floor(this.totalLength / this.paginator.pageSize) == this.pageIndex){
      this.pageIndex = Math.max(0, this.pageIndex - 1);
    }
    this.fetchRatings({page: this.pageIndex, size: this.paginator.pageSize});
  }


}
