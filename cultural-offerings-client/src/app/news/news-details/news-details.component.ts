import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientImage } from '../../../app/core/model/client-image';
import { CulturalOffering } from '../../../app/core/model/cultural-offering';
import { CulturalOfferingSubtype } from '../../../app/core/model/cultural-offering-subtype';
import { CulturalOfferingType } from '../../../app/core/model/cultural-offering-type';
import { ImageService } from '../../../app/core/services/image/image.service';
import { CulturalOfferingSubtypeService } from '../../../app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CulturalOfferingService } from '../../../app/core/services/cultural-offering/cultural-offering.service';
import { ImageModel } from '../../../app/core/model/image-model';
import { SimpleSnackbarComponent } from '../../../app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';
import { Location } from '../../../app/core/model/location';
import { News } from 'src/app/core/model/news';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { NewsService } from 'src/app/core/services/news/news.service';

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.scss']
})
export class NewsDetailsComponent implements OnInit {

  @Input()
  news!: News;

  culturalOfferingId: number;

  @Output()
  upsertLocal: EventEmitter<News> = new EventEmitter<News>();

  loading = false;
  newsForm: FormGroup;
  imageModels: ImageModel[] = []; // image model sa servera
  images: ClientImage[] = []; // reprezentacija slike pogodna za carousel komponentu
  imagesLoading = false;
  submitAttempted = false;
  private author: number;

  constructor(
    public formBuilder: FormBuilder,
    public imageService: ImageService,
    public newsService: NewsService,
    public matSnackBar: MatSnackBar,
    public authService: AuthService) { }

  ngOnInit(): void {
    this.newsForm = this.formBuilder.group({
      title: [this.news ? this.news.title : '', Validators.required],
      emailText: [this.news ? this.news.text : '', Validators.required]
    });
    this.fetchImages();
    this.culturalOfferingId = this.newsService.getSelectedOfferingId();
  }

  async fetchImages(): Promise<void> {
    if (this.news) {
      this.imagesLoading = true;
      const fetchImagePromises: Promise<ImageModel>[] = [];
      this.news.images.forEach(id => {
        fetchImagePromises.push(this.imageService.getById(id).toPromise());
      });
      this.imageModels = await Promise.all(fetchImagePromises);
      this.images = this.imageModels.map(imageModel => ({
        retrievedImage: 'data:image/jpeg;base64,' + imageModel.picByte
      }));
      this.imagesLoading = false;
    }
  }

  insertImageLocal(clientImage: ClientImage): void {
    this.images.push(clientImage);
    this.imageModels.push({ picByte: clientImage.retrievedImage });
  }

  removeImageLocal(imageIndex: number): void {
    this.images.splice(imageIndex, 1);
    this.imageModels.splice(imageIndex, 1);
  }

  getUploadImagesPromise(): Promise<ImageModel[]> {
    // upload radimo za slike koje imaju odabran fajl
    const filesForUpload = this.images.filter(clientImage => clientImage.selectedFile).map(clientImage => clientImage.selectedFile);
    const imageUploadPromises = filesForUpload.map(file => this.imageService.uploadAsPromise(file));
    return Promise.all(imageUploadPromises);
  }

  async getUpdateNewsPromise(imageModelIds: number[]): Promise<News> {
    const updatedNews: News = {
      id: this.news.id,
      title: this.newsForm.value.title,
      text: this.newsForm.value.emailText,
      date: new Date(),
      images: this.news.images.filter(imageId => this.imageModels.find(x => x.id == imageId)).concat(imageModelIds),
      culturalOffering: this.culturalOfferingId,
      user: this.author
    };
    return this.newsService.update(updatedNews).toPromise();
  }

  async getInsertNewsPromise(imageModelIds: number[]): Promise<News> {
    const createdNews: News = {
      id: null,
      title: this.newsForm.value.title,
      text: this.newsForm.value.emailText,
      date: new Date(),
      images: imageModelIds,
      culturalOffering: this.culturalOfferingId,
      user: this.author
    };
    return this.newsService.insert(createdNews).toPromise();
  }

  upsert(): void {
    this.submitAttempted = true;
    if (this.newsForm.invalid) {
      return;
    }

    this.author = this.authService.getUserId();

    if (this.news) {
      this.update();
    } else {
      this.insert();
    }
  }

  async update(): Promise<void> {
    this.loading = true;
    try {
      const uploadedImages = await this.getUploadImagesPromise();
      const imageModelIds = uploadedImages.map(imageModel => imageModel.id);
      const updatedNews = await this.getUpdateNewsPromise(imageModelIds);
      this.upsertLocal.emit(updatedNews);
      this.showSnackbar('USPESNA IZMENA', `Email je uspesno izmenjen i poslat svim pretplacenim korisnicima.`, true);
      await this.newsService.notify(updatedNews.id).toPromise();
    } catch ({ error }) {
      // show toast
      this.upsertLocal.emit(this.news);
      this.showSnackbar('NEUSPESNA IZMENA', `${error.message}`, false);
    }
    this.loading = false;
  }

  async insert(): Promise<void> {
    this.loading = true;
    try {
      const uploadedImages = await this.getUploadImagesPromise();
      const imageModelIds = uploadedImages.map(imageModel => imageModel.id);
      const insertedNews = await this.getInsertNewsPromise(imageModelIds);
      this.upsertLocal.emit(insertedNews);
      this.showSnackbar('USPESNO KREIRANJE', `Email je uspesno kreiran i poslat svim pretplacenim korisnicima.`, true);
      await this.newsService.notify(insertedNews.id).toPromise();
    } catch ({ error }) {
      // show toast
      this.upsertLocal.emit(this.news);
      this.showSnackbar('NEUSPESNO DODAVANJE', `${error.message}`, false);
    }
    this.loading = false;
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
