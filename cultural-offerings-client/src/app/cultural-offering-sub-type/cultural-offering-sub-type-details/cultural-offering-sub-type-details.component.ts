import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { CulturalOfferingSubtype } from 'src/app/core/model/cultural-offering-subtype';
import { CulturalOfferingType } from 'src/app/core/model/cultural-offering-type';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { SimpleSnackbarComponent } from 'src/app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';

@Component({
  selector: 'app-cultural-offering-sub-type-details',
  templateUrl: './cultural-offering-sub-type-details.component.html',
  styleUrls: ['./cultural-offering-sub-type-details.component.scss'],
  providers: [
    CulturalOfferingSubtypeService
  ]
})
export class CulturalOfferingSubTypeDetailsComponent implements OnInit {

  @Input()
  culturalOfferingSubType!: CulturalOfferingSubtype;

  @Input()
  culturalOfferingTypes: CulturalOfferingType[];

  @Output()
  upsertLocal: EventEmitter<CulturalOfferingSubtype> = new EventEmitter<CulturalOfferingSubtype>();

  culturalOfferingSubTypeForm: FormGroup;
  loading: boolean;
  
  constructor(
    private formBuilder: FormBuilder, 
    private culturalOfferingSubTypeService: CulturalOfferingSubtypeService,
    private matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.culturalOfferingSubTypeForm = this.formBuilder.group({
      subTypeName: [this.culturalOfferingSubType ? this.culturalOfferingSubType.subTypeName : '', Validators.required],
      typeId: [this.culturalOfferingSubType ? this.culturalOfferingSubType.typeId : '', Validators.required],
    });
  }

  async getUpdateCulturalOfferingSubTypePromises(): Promise<CulturalOfferingSubtype>{
    const culturalOfferingSubType: CulturalOfferingSubtype = {
      id: this.culturalOfferingSubType.id,
      subTypeName: this.culturalOfferingSubTypeForm.value.subTypeName,
      typeId: this.culturalOfferingSubTypeForm.value.typeId
    };
    return this.culturalOfferingSubTypeService.update(culturalOfferingSubType).toPromise();
  }

  async getInsertCulturalOfferingSubTypePromises(): Promise<CulturalOfferingSubtype>{
    const culturalOfferingSubType: CulturalOfferingSubtype = {
      id: null,
      subTypeName: this.culturalOfferingSubTypeForm.value.subTypeName,
      typeId: this.culturalOfferingSubTypeForm.value.typeId,
    };
    return this.culturalOfferingSubTypeService.insert(culturalOfferingSubType).toPromise();
  }

  upsert(){
    if(this.culturalOfferingSubType){
      this.update();
    }else{
      this.insert();
    }
  }

  async update(): Promise<void> {
    this.loading = true;
    try{
      let updatedCulturalOfferingSubType: CulturalOfferingSubtype = await this.getUpdateCulturalOfferingSubTypePromises();
      this.upsertLocal.emit(updatedCulturalOfferingSubType);
      this.showSnackbar('USPESNA IZMENA', `Podtip kategorije pod nazivom ${updatedCulturalOfferingSubType.subTypeName} je uspesno promenjen`, true);
    }catch({error}){
      //show toast
      console.log(error);
      this.upsertLocal.emit(this.culturalOfferingSubType);
      this.showSnackbar('NEUSPESNA IZMENA', `${error.message}`, false);
    }
    this.loading = false;
  }

  async insert(){
    this.loading = true;
    try{
      let insertedCulturalOfferingSubType: CulturalOfferingSubtype = await this.getInsertCulturalOfferingSubTypePromises();
      this.upsertLocal.emit(insertedCulturalOfferingSubType);
      this.showSnackbar('USPESNO DODAVANJE', `Podtip kategorije pod nazivom ${insertedCulturalOfferingSubType.subTypeName} je uspesno dodat`, true);
    }catch({error}){
      //show toast
      this.upsertLocal.emit(this.culturalOfferingSubType);
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
