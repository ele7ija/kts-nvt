import { Component, OnInit } from '@angular/core';
import { CulturalOfferingType } from '../../../model/cultural-offering-type/cultural-offering-type';
import { CulturalOfferingTypeService } from '../../../services/cultural-offering-type/cultural-offering-type.service';

@Component({
  selector: 'app-cultural-offering-type',
  templateUrl: './cultural-offering-type.component.html',
  styleUrls: ['./cultural-offering-type.component.scss']
})
export class CulturalOfferingTypeComponent implements OnInit {

  culturalOfferingTypes: CulturalOfferingType[];
  fetchSuccess: boolean;
  isLoading: boolean = true;

  constructor(private culturalOfferingTypeService: CulturalOfferingTypeService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.culturalOfferingTypeService
      .getAll()
      .subscribe(
        (data: CulturalOfferingType[]) => {
          this.culturalOfferingTypes = data;
          this.fetchSuccess = true;
          setTimeout(() => this.isLoading = false, 500);
        },
        (error: any) => {
          this.fetchSuccess = false;
          setTimeout(() => this.isLoading = false, 500);
        }
      );
  }

}
