import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientImage } from '../../../app/core/model/client-image';
import { CulturalOffering } from '../../../app/core/model/cultural-offering';
import { CulturalOfferingSubtype } from '../../../app/core/model/cultural-offering-subtype';
import { CulturalOfferingType } from '../../../app/core/model/cultural-offering-type';
import { ImageService } from '../../../app/core/services/image/image.service';
import { CulturalOfferingSubtypeService } from '../../../app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from '../../../app/core/services/cultural-offering-type/cultural-offering-type.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CulturalOfferingService } from '../../../app/core/services/cultural-offering/cultural-offering.service';
import { ImageModel } from '../../../app/core/model/image-model';
import { SimpleSnackbarComponent } from '../../../app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';

@Component({
  selector: 'app-cultural-offering-details',
  templateUrl: './cultural-offering-details.component.html',
  styleUrls: ['./cultural-offering-details.component.scss']
})
export class CulturalOfferingDetailsComponent implements OnInit {

  @Input()
  culturalOffering!: CulturalOffering;

  @Input()
  culturalOfferingTypes: CulturalOfferingType[];

  @Input()
  culturalOfferingSubtypes: CulturalOfferingSubtype[];

  @Output()
  upsertLocal: EventEmitter<CulturalOffering> = new EventEmitter<CulturalOffering>();

  loading: boolean = false;
  culturalOfferingForm: FormGroup;
  imageModels: ImageModel[] = []; //image model sa servera
  images: ClientImage[] = []; //reprezentacija slike pogodna za carousel komponentu
  imagesLoading: boolean = false;

  constructor(
    private formBuilder: FormBuilder, 
    private imageService: ImageService,
    private culturalOfferingService: CulturalOfferingService,
    private culturalOfferingSubTypeService: CulturalOfferingSubtypeService,
    private culturalOfferingTypeService: CulturalOfferingTypeService,
    private matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.culturalOfferingForm = this.formBuilder.group({
      name: [this.culturalOffering ? this.culturalOffering.name : '', Validators.required],
      description: [this.culturalOffering ? this.culturalOffering.description : ''],
      culturalOfferingTypeName: [this.culturalOffering ? this.culturalOffering.culturalOfferingTypeName : ''],
      culturalOfferingSubtypeName: [this.culturalOffering ? this.culturalOffering.culturalOfferingSubtypeName : ''],
    });
    this.fetchImages();
  }

  async fetchImages(): Promise<void> {
    if(this.culturalOffering){
      this.imagesLoading = true;
      const fetchImagePromises: Promise<ImageModel>[] = [];
      this.culturalOffering.imageIds.forEach(id => {
        fetchImagePromises.push(this.imageService.getById(id).toPromise());
      });
      this.imageModels = await Promise.all(fetchImagePromises);
      this.images = this.imageModels.map(imageModel => ({
        retrievedImage: 'data:image/jpeg;base64,' + imageModel.picByte
      }));
      this.imagesLoading = false;
    }
  }

  insertImageLocal(clientImage: ClientImage){
    this.images.push(clientImage);
    this.imageModels.push({picByte: clientImage.retrievedImage});
  }

  removeImageLocal(imageIndex: number){
    this.images.splice(imageIndex, 1);
    this.imageModels.splice(imageIndex, 1);
  }

  async fetchCulturalOfferingSubTypes(): Promise<void>{
    const {id} = this.culturalOfferingTypes.find(
      culturalOfferingType => culturalOfferingType.typeName == this.culturalOfferingForm.value.culturalOfferingTypeName
    );
    this.culturalOfferingSubtypes = await this.culturalOfferingSubTypeService.getAllByTypeId(id).toPromise();
  }

  async getUploadImagesPromise(): Promise<ImageModel[]>{
    //upload radimo za slike koje imaju odabran fajl
    const filesForUpload = this.images.filter(clientImage => clientImage.selectedFile).map(clientImage => clientImage.selectedFile);
    const imageUploadPromises = filesForUpload.map(this.imageService.uploadAsPromise);
    return Promise.all(imageUploadPromises);
  }

  async getUpdateCulturalOfferingPromise(imageModelIds: number[]): Promise<CulturalOffering>{
    const culturalOffering: CulturalOffering = {
      id: this.culturalOffering.id,
      name: this.culturalOfferingForm.value.name,
      description: this.culturalOfferingForm.value.description,
      locationId: this.culturalOffering.locationId,
      imageIds: this.culturalOffering.imageIds.concat(imageModelIds),
      culturalOfferingTypeName: this.culturalOfferingForm.value.culturalOfferingTypeName,
      culturalOfferingSubtypeName: this.culturalOfferingForm.value.culturalOfferingSubtypeName
    };
    return this.culturalOfferingService.update(culturalOffering).toPromise();
  }

  async getInsertCulturalOfferingPromise(imageModelIds: number[]): Promise<CulturalOffering>{
    const culturalOffering: CulturalOffering = {
      id: null,
      name: this.culturalOfferingForm.value.name,
      description: this.culturalOfferingForm.value.description,
      locationId: null,
      imageIds: imageModelIds,
      culturalOfferingTypeName: this.culturalOfferingForm.value.culturalOfferingTypeName,
      culturalOfferingSubtypeName: this.culturalOfferingForm.value.culturalOfferingSubtypeName
    };
    return this.culturalOfferingTypeService.insert(culturalOffering).toPromise();
  }

  upsert(){
    console.log("USAO GDE NE TREBA");
    if(this.culturalOffering){
      this.update();
    }else{
      this.insert();
    }
  }

  async update(): Promise<void> {
    this.loading = true;
    try{
      let uploadedImages = await this.getUploadImagesPromise();
      const imageModelIds = uploadedImages.map(imageModel => imageModel.id);
      const updatedCulturalOffering = await this.getUpdateCulturalOfferingPromise(imageModelIds);
      this.upsertLocal.emit(updatedCulturalOffering);
      this.showSnackbar('USPESNA IZMENA', `Kulturna ponuda pod nazivom ${updatedCulturalOffering.name} je uspesno promenjena`, true);
    }catch({error}){
      //show toast
      this.upsertLocal.emit(this.culturalOffering);
      this.showSnackbar('NEUSPESNA IZMENA', `${error.message}`, false);
    }
    this.loading = false;
  }

  async insert(){
    this.loading = true;
    try{
      let uploadedImages = await this.getUploadImagesPromise();
      const imageModelIds = uploadedImages.map(imageModel => imageModel.id);
      const insertedCulturalOffering = await this.getInsertCulturalOfferingPromise(imageModelIds);
      this.upsertLocal.emit(insertedCulturalOffering);
      this.showSnackbar('USPESNO DODAVANJE', `Kulturna ponuda pod nazivom ${insertedCulturalOffering.name} je uspesno dodata`, true);
    }catch({error}){
      //show toast
      this.upsertLocal.emit(this.culturalOffering);
      this.showSnackbar('NEUSPESNO DODAVANJE', `${error.message}`, false);
    }
    this.loading = false;
  }

  showSnackbar(title: string, message: string, success: boolean) {
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