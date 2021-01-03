import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CulturalOfferingSubtype } from 'src/app/model/cultural-offering-subtype/cultural-offering-subtype';
import { CulturalOfferingType, CulturalOfferingTypeUpdate } from 'src/app/model/cultural-offering-type/cultural-offering-type';
import { ListChangeEventType } from 'src/app/model/event/list-change-event-type/list-change-event-type.enum';
import { ListChangeEvent } from 'src/app/model/event/list-change-event/list-change-event';
import { ImageModel } from 'src/app/model/image-model/image-model';
import { CulturalOfferingSubtypeService } from 'src/app/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from 'src/app/services/cultural-offering-type/cultural-offering-type.service';
import { ImageService } from 'src/app/services/image/image.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition
} from '@angular/material/snack-bar';
import { SimpleSnackbarComponent } from '../../snackbar/simple-snackbar/simple-snackbar.component';

@Component({
  selector: 'app-cultrual-offering-type-details',
  templateUrl: './cultrual-offering-type-details.component.html',
  styleUrls: ['./cultrual-offering-type-details.component.scss']
})
export class CultrualOfferingTypeDetailsComponent implements OnInit {

  @Input()
  culturalOfferingType!: CulturalOfferingType;

  @Output()
  updateLocal: EventEmitter<CulturalOfferingType> = new EventEmitter<CulturalOfferingType>();

  culturalOfferingTypeForm: FormGroup;
  errorMsg : string;
  successMsg: string;

  imageLoading: boolean;
  imageId: number;
  retrievedImage: any;
  base64Data: any;
  selectedFile: File;
  fileButtonWidth: any;
  defaultImagepath: string = "../../../../assets/images/empty.png";

  culturalOfferingSubtypes: CulturalOfferingSubtype[];

  loading: boolean = false;

  constructor(
    private formBuilder: FormBuilder, 
    private imageService: ImageService,
    private culturalOfferingSubTypeService: CulturalOfferingSubtypeService,
    private culturalOfferingTypeService: CulturalOfferingTypeService,
    private matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.culturalOfferingTypeForm = this.formBuilder.group({
      typeName: [this.culturalOfferingType.typeName, Validators.required],
    });
    this.fetchImage();
    this.fetchCulturalOfferingSubTypes();
    
  }

  fetchImage(): void {
    if(this.culturalOfferingType.imageId){
      this.imageLoading = true;
      this.imageId = this.culturalOfferingType.imageId;
      this.imageService.getById(this.culturalOfferingType.imageId).subscribe((imageModel: ImageModel) => {
        this.base64Data = imageModel.picByte;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        this.calculateFileButtonWidth(this.retrievedImage);
        this.imageLoading = false;
      });
    }else{
      this.retrievedImage = null;
      this.calculateFileButtonWidth(this.defaultImagepath);
    }
  }

  fetchCulturalOfferingSubTypes(): void{
    this.culturalOfferingSubTypeService.getAllByTypeId(this.culturalOfferingType.id)
      .subscribe((culturalOfferingSubtypes: CulturalOfferingSubtype[]) => {
        this.culturalOfferingSubtypes = [...culturalOfferingSubtypes];
      });
  }

  calculateFileButtonWidth(path: string){
    const img = new Image();
    let that = this;
    img.onload = function() {
      if(img.width > 280)
        that.fileButtonWidth = 280;
      else
        that.fileButtonWidth = img.width;
    }
    img.src = path;
  }

  onFileChanged(event): void{
    this.selectedFile = event.target.files[0];
    var reader = new FileReader();
    let that = this;
    reader.onload = function (e) {
      that.retrievedImage = e.target.result;
      that.calculateFileButtonWidth(that.retrievedImage);
    };
    reader.readAsDataURL(this.selectedFile);
  }

  deselectImage(): void{
    this.selectedFile = null;
    this.retrievedImage = null;
    this.imageId = null;
    this.calculateFileButtonWidth(this.defaultImagepath);
  }

  changeCulturalOfferingSubtypes(event: ListChangeEvent<CulturalOfferingSubtype>): void{
    if(event.listChangeEventType == ListChangeEventType.ADD){
      if(!this.culturalOfferingSubtypes.find((value: CulturalOfferingSubtype) => event.item.subTypeName == value.subTypeName)){
        this.culturalOfferingSubtypes.push(event.item);
      }
    }else if(event.listChangeEventType == ListChangeEventType.REMOVE){
      this.culturalOfferingSubtypes = this.culturalOfferingSubtypes
        .filter((value: CulturalOfferingSubtype) => event.item.subTypeName != value.subTypeName);
    }
  }

  async getUploadImagePromise(): Promise<ImageModel>{
    let uploadImagePromise: Promise<ImageModel>;
    if(this.selectedFile)
      uploadImagePromise = this.imageService.upload(this.selectedFile).toPromise();
    else if(this.imageId)
      uploadImagePromise = new Promise<ImageModel>((resolve, reject) => resolve({id: this.imageId}));
    else
      uploadImagePromise = new Promise<ImageModel>((resolve, reject) => resolve(null));
    return uploadImagePromise;
  }

  async getUpdateCulturalOfferingTypePromises(imageModelId: number): Promise<CulturalOfferingType>{
    console.log(this.culturalOfferingSubtypes);
    const culturalOfferingType: CulturalOfferingTypeUpdate = {
      id: this.culturalOfferingType.id,
      typeName: this.culturalOfferingTypeForm.value.typeName,
      imageId: imageModelId,
      subTypeIds: this.culturalOfferingSubtypes.filter((item: CulturalOfferingSubtype) => !!item.id).map((item: CulturalOfferingSubtype) => item.id),
      subTypesToAdd: this.culturalOfferingSubtypes.filter((item: CulturalOfferingSubtype) => !item.id).map((item: CulturalOfferingSubtype) => item.subTypeName)
    };
    return this.culturalOfferingTypeService.update(culturalOfferingType).toPromise();
  }

  async update(): Promise<void> {
    this.loading = true;
    try{
      let uploadedImage: ImageModel = await this.getUploadImagePromise();
      const imageModelId:  number = !!uploadedImage ? uploadedImage.id : null;
      let updatedCulturalOfferingType: CulturalOfferingType = await this.getUpdateCulturalOfferingTypePromises(imageModelId);
      this.updateLocal.emit(updatedCulturalOfferingType);
      this.showSnackbar('UPDATE SUCCESS', `${updatedCulturalOfferingType.typeName} has been successfully changed`, true);
    }catch(error){
      //show toast
      this.updateLocal.emit(this.culturalOfferingType);
      this.showSnackbar('UPDATE FAILED', `${error.message}`, false);
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
