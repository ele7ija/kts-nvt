import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CulturalOfferingSubtype } from 'src/app/core/model/cultural-offering-subtype';
import { CulturalOfferingType, CulturalOfferingTypeUpsert } from 'src/app/core/model/cultural-offering-type';
import { ListChangeEventType } from 'src/app/core/model/list-change-event-type.enum';
import { ListChangeEvent } from 'src/app/core/model/list-change-event';
import { ImageModel } from 'src/app/core/model/image-model';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { ImageService } from 'src/app/core/services/image/image.service';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  upsertLocal: EventEmitter<CulturalOfferingType> = new EventEmitter<CulturalOfferingType>();

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
      typeName: [this.culturalOfferingType ? this.culturalOfferingType.typeName : '', Validators.required],
    });
    this.fetchImage();
    this.fetchCulturalOfferingSubTypes();
    
  }

  fetchImage(): void {
    if(this.culturalOfferingType && this.culturalOfferingType.imageId){
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
    if(this.culturalOfferingType){
      this.culturalOfferingSubTypeService.getAllByTypeId(this.culturalOfferingType.id)
      .subscribe((culturalOfferingSubtypes: CulturalOfferingSubtype[]) => {
        this.culturalOfferingSubtypes = [...culturalOfferingSubtypes];
      });
    }else{
      this.culturalOfferingSubtypes = [];
    }
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
    const culturalOfferingType: CulturalOfferingTypeUpsert = {
      id: this.culturalOfferingType.id,
      typeName: this.culturalOfferingTypeForm.value.typeName,
      imageId: imageModelId,
      subTypeIds: this.culturalOfferingSubtypes.filter((item: CulturalOfferingSubtype) => !!item.id).map((item: CulturalOfferingSubtype) => item.id),
      subTypesToAdd: this.culturalOfferingSubtypes.filter((item: CulturalOfferingSubtype) => !item.id).map((item: CulturalOfferingSubtype) => item.subTypeName)
    };
    return this.culturalOfferingTypeService.update(culturalOfferingType).toPromise();
  }

  async getInsertCulturalOfferingTypePromises(imageModelId: number): Promise<CulturalOfferingType>{
    const culturalOfferingType: CulturalOfferingTypeUpsert = {
      typeName: this.culturalOfferingTypeForm.value.typeName,
      imageId: imageModelId,
      subTypeIds: [],
      subTypesToAdd: this.culturalOfferingSubtypes.map((item: CulturalOfferingSubtype) => item.subTypeName)
    };
    return this.culturalOfferingTypeService.insert(culturalOfferingType).toPromise();
  }

  upsert(){
    if(this.culturalOfferingType){
      this.update();
    }else{
      this.insert();
    }
  }

  async update(): Promise<void> {
    this.loading = true;
    try{
      let uploadedImage: ImageModel = await this.getUploadImagePromise();
      const imageModelId:  number = !!uploadedImage ? uploadedImage.id : null;
      let updatedCulturalOfferingType: CulturalOfferingType = await this.getUpdateCulturalOfferingTypePromises(imageModelId);
      this.upsertLocal.emit(updatedCulturalOfferingType);
      this.showSnackbar('USPESNA IZMENA', `Tip kategorije pod nazivom ${updatedCulturalOfferingType.typeName} je uspesno promenjen`, true);
    }catch({error}){
      //show toast
      this.upsertLocal.emit(this.culturalOfferingType);
      this.showSnackbar('NEUSPESNA IZMENA', `${error.message}`, false);
    }
    this.loading = false;
  }

  async insert(){
    this.loading = true;
    try{
      let uploadedImage: ImageModel = await this.getUploadImagePromise();
      const imageModelId:  number = !!uploadedImage ? uploadedImage.id : null;
      let insertedCulturalOfferingType: CulturalOfferingType = await this.getInsertCulturalOfferingTypePromises(imageModelId);
      this.upsertLocal.emit(insertedCulturalOfferingType);
      this.showSnackbar('USPESNO DODAVANJE', `Tip kategorije pod nazivom ${insertedCulturalOfferingType.typeName} je uspesno dodat`, true);
    }catch({error}){
      //show toast
      this.upsertLocal.emit(this.culturalOfferingType);
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
