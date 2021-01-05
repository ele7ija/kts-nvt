import { Injectable } from '@angular/core';
import { ApiService } from '../security/api-service/api.service';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { PageableRequest } from 'src/app/core/model/pageable-request';
import { CulturalOfferingType, CulturalOfferingTypeUpsert } from 'src/app/core/model/cultural-offering-type';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferingTypeService {

  private endpoint: string = environment.baseUrl + '/cultural-offerings-types'

  constructor(private apiService: ApiService) {}

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }

  insert(culturalOfferingType: CulturalOfferingTypeUpsert): Observable<any> {
    return this.apiService.post(`${this.endpoint}/refreshSubTypes`, culturalOfferingType);
  }

  update(culturalOfferingTypeUpdate: CulturalOfferingTypeUpsert): Observable<CulturalOfferingType>{
    return this.apiService.put(`${this.endpoint}/refreshSubTypes/${culturalOfferingTypeUpdate.id}`, culturalOfferingTypeUpdate);
  }

  delete(id: number): Observable<void>{
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }
}
