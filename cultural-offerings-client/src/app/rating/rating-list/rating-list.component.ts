import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { PageableRequest } from 'src/app/core/model/pageable-request';
import { Rating } from '../../../app/core/model/rating';
import { RatingService } from '../../../app/core/services/rating/rating.service';

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

  constructor(
    private ratingService: RatingService,
  ) { }

  ngOnChanges(changes: SimpleChanges) {
    if(changes.culturalOfferingId.currentValue){
      this.fetchRatings({page: 0, size: this.pageSizeOptions[0]});  
    }
  }

  async fetchRatings(pageableRequest: PageableRequest){
    this.loading = true;
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


}
