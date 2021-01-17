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

  constructor(
    private activeRoute: ActivatedRoute,
    private culturalOfferingService: CulturalOfferingService,
    private matSnackbar: MatSnackBar,
    private imageService: ImageService
  ) { }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(async params => {
      const id = params.get('id');
      try{
        this.culturalOffering = await this.culturalOfferingService.getOne(id).toPromise();
        this.fetchImages();
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

}
