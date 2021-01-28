import { Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { PageableRequest } from 'src/app/core/model/pageable-request';
import { RatingInput } from '../../../app/core/model/rating-input';
import { PaginatorComponent } from 'src/app/shared/modules/paginator/paginator.component';
import { Rating } from '../../../app/core/model/rating';
import { RatingService } from '../../../app/core/services/rating/rating.service';
import { AuthService } from '../../../app/core/services/security/auth-service/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SimpleSnackbarComponent } from 'src/app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';

@Component({
  selector: 'app-rating-list',
  templateUrl: './rating-list.component.html',
  styleUrls: ['./rating-list.component.scss']
})
export class RatingListComponent implements OnChanges {

  @Input()
  culturalOfferingId: number;

  ratings: Rating[] = [];
  userRating: Rating;
  userRatingBeingUploaded = false;
  totalLength: number;
  loading = false;
  pageSizeOptions: number[] = [5, 10, 20];
  pageIndex = 0;

  @ViewChild(PaginatorComponent) paginator: PaginatorComponent;

  constructor(
    public ratingService: RatingService,
    public authService: AuthService,
    public matSnackBar: MatSnackBar
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.culturalOfferingId.currentValue) {
      this.fetchRatings({ page: 0, size: this.pageSizeOptions[0] });
    }
  }

  async fetchRatings(pageableRequest: PageableRequest): Promise<void> {
    this.loading = true;
    this.pageIndex = pageableRequest.page;
    pageableRequest.sort = 'date';
    pageableRequest.sortOrder = 'desc';
    if (this.culturalOfferingId) {
      try {
        const pageableResponse = await this.ratingService.getAllByCulturalOfferingId(pageableRequest, this.culturalOfferingId).toPromise();
        const tempRatings: Rating[] = pageableResponse.content;
        this.ratings = tempRatings.filter(rating => rating.userId != this.authService.getUserId());
        this.userRating = tempRatings.find(rating => rating.userId == this.authService.getUserId());
        this.totalLength = pageableResponse.totalElements;
        if (this.userRating) {
          this.totalLength -= 1;
        }
      } catch {

      }
    }
    this.loading = false;
  }

  async ratingAddedEvent(ratingInput: RatingInput): Promise<void> {
    try {
      this.userRatingBeingUploaded = true;
      this.userRating = await this.ratingService.insert({ value: ratingInput.value, date: ratingInput.date, culturalOfferingId: this.culturalOfferingId, userId: this.authService.getUserId() }).toPromise();
      setTimeout(
        () => {
          this.userRatingBeingUploaded = false;
          this.showSnackbar('USPESNO OCENJIVANJE', `Uspesno ste ocenili kulturnu ponudu`, true);
        },
        1000
      );

    } catch (error) {
      this.showSnackbar('GRESKA', `${error.message}`, false);
      this.userRatingBeingUploaded = false;
    }
  }

  async removeRatingEvent(rating: Rating): Promise<void> {
    await this.ratingService.delete(rating.id).toPromise();
    if (this.totalLength % this.paginator.pageSize == 1 && Math.floor(this.totalLength / this.paginator.pageSize) == this.pageIndex) {
      this.pageIndex = Math.max(0, this.pageIndex - 1);
    }
    this.fetchRatings({ page: this.pageIndex, size: this.paginator.pageSize });
    this.showSnackbar('USPESNO BRISANJE', `Uspesno ste obrisali ocenu za kulturnu ponudu`, true);
  }

  showSnackbar(title: string, message: string, success: boolean): void {
    this.matSnackBar.openFromComponent(SimpleSnackbarComponent, {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000,
      data: {
        title,
        message,
        success,
      },

    });
  }
}
