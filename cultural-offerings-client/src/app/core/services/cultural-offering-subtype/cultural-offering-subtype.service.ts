import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CulturalOfferingSubtype } from 'src/app/core/model/cultural-offering-subtype';
import { environment } from 'src/environments/environment';
import { ApiService } from '../security/api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferingSubtypeService {

  endpoint: string = `${environment.baseUrl}/cultural-offering-subtypes`

  constructor(private apiService: ApiService) { }

  getAllByTypeId(typeId: number): Observable<CulturalOfferingSubtype[]>{
    return this.apiService.get(`${this.endpoint}/byTypeId/${typeId}`);
  }
}
