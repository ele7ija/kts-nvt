import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CulturalOfferingType } from 'src/app/model/cultural-offering-type/cultural-offering-type';

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

  newTypeName!: string

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.newTypeName = this.culturalOfferingType.typeName;
    this.culturalOfferingTypeForm = this.formBuilder.group({
      typeName: [this.newTypeName, Validators.required],
    });
  }

  change(): void {

  }

}
