import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Rating } from '../../../app/core/model/rating';
import { RatingService } from '../../../app/core/services/rating/rating.service';
import { ClientImage } from '../../../app/core/model/client-image';
import { CulturalOffering } from '../../../app/core/model/cultural-offering';
import { ImageModel } from '../../../app/core/model/image-model';
import { CommentService } from '../../../app/core/services/comment/comment.service';
import { CulturalOfferingService } from '../../../app/core/services/cultural-offering/cultural-offering.service';
import { ImageService } from '../../../app/core/services/image/image.service';
import { SimpleSnackbarComponent } from '../../../app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';
import { SubscriptionService } from 'src/app/core/services/subscription/subscription.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { Subscription } from 'src/app/core/model/subscription';

@Component({
  selector: 'app-cultural-offering-page',
  templateUrl: './cultural-offering-page.component.html',
  styleUrls: ['./cultural-offering-page.component.scss']
})
export class CulturalOfferingPageComponent implements OnInit {

  culturalOffering: CulturalOffering;

  imagesLoading: boolean = false;
  images: ClientImage[] = []; //reprezentacija slike pogodna za carousel komponentu
  ratings: Rating[];

  subscription: Subscription;

  constructor(
    private activeRoute: ActivatedRoute,
    private culturalOfferingService: CulturalOfferingService,
    private matSnackbar: MatSnackBar,
    private imageService: ImageService,
    private subscriptionService: SubscriptionService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(async params => {
      const id = params.get('id');
      try{
        this.culturalOffering = await this.culturalOfferingService.getOne(id).toPromise();
        this.fetchImages();
        let userId: number = this.authService.getUserId();
        if (userId) {
          this.subscriptionService.getQuery(this.culturalOffering.id, userId)
          .subscribe((subscriptions: Subscription[]) => {
            this.subscription = subscriptions[0];
          });
        }
      }catch(error){
        this.showSnackbar(`Error`, `Cultural offering with id ${id} could not be loaded.`, false);
      }
    });
  }


  async fetchImages(): Promise<void> {
    if(this.culturalOffering){
      this.imagesLoading = true;
      const fetchImagePromises: Promise<ImageModel>[] = [];
      this.culturalOffering.imageIds.forEach(id => {
        fetchImagePromises.push(this.imageService.getById(id).toPromise());
      });
      const imageModels = await Promise.all(fetchImagePromises);
      this.images = imageModels.map(imageModel => ({
        retrievedImage: 'data:image/jpeg;base64,' + imageModel.picByte
      }));
      this.imagesLoading = false;
    }
  }

  showSnackbar(title: string, message: string, success: boolean) {
    this.matSnackbar.openFromComponent(SimpleSnackbarComponent, {
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

  subscribeClicked(event: boolean): void {
    if (this.subscription) {
      this.subscriptionService.delete(this.subscription.id)
      .subscribe(() => {
        this.subscription = null;
        this.showSnackbar('Odjavili ste pretplatu', '', true);
      })
    }
    else {
      this.subscriptionService.insert(
        {
          id: null,
          user: this.authService.getUserId(),
          culturalOffering: this.culturalOffering.id
        }
      )
      .subscribe((s: Subscription) => {
        this.subscription = s;
        this.showSnackbar('Pretplatili ste se', '', true);
      })
    }
  }

  loggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
