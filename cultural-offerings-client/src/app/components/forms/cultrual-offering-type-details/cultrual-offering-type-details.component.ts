import { typeWithParameters } from '@angular/compiler/src/render3/util';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CulturalOfferingType } from 'src/app/model/cultural-offering-type/cultural-offering-type';
import { ImageModel } from 'src/app/model/image-model/image-model';
import { ImageService } from 'src/app/services/image/image.service';

@Component({
  selector: 'app-cultrual-offering-type-details',
  templateUrl: './cultrual-offering-type-details.component.html',
  styleUrls: ['./cultrual-offering-type-details.component.scss']
})
export class CultrualOfferingTypeDetailsComponent implements OnInit {

  @Input()
  culturalOfferingType!: CulturalOfferingType;

  culturalOfferingTypeForm: FormGroup;
  errorMsg : string;
  successMsg: string;

  newTypeName!: string;
  retrievedImage: any;
  base64Data: any;
  selectedFile: File;

  fileButtonWidth: any;

  constructor(
    private formBuilder: FormBuilder, 
    private imageService: ImageService) { }

  ngOnInit(): void {
    this.newTypeName = this.culturalOfferingType.typeName;
    this.culturalOfferingTypeForm = this.formBuilder.group({
      typeName: [this.newTypeName, Validators.required],
    });
    if(this.culturalOfferingType.imageId){
      this.imageService.getById(this.culturalOfferingType.imageId).subscribe((imageModel: ImageModel) => {
        this.base64Data = imageModel.picByte;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        this.calculateFileButtonWidth(this.retrievedImage);
      });
    }else{
      this.retrievedImage = null;
      this.calculateFileButtonWidth("../../../../assets/images/empty.png");
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

  update(): void {

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

}
