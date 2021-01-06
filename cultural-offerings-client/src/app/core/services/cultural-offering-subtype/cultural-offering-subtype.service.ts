import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CulturalOfferingSubtype } from 'src/app/core/model/cultural-offering-subtype';
import { environment } from 'src/environments/environment';
import { AbstractCrudService } from '../../model/abstract-crud-service';
import { PageableRequest } from '../../model/pageable-request';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class CulturalOfferingSubtypeService implements AbstractCrudService<CulturalOfferingSubtype> {

  endpoint: string = `${environment.baseUrl}/cultural-offering-subtypes`

  constructor(private apiService: ApiService) { }

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }

  insert(entity: CulturalOfferingSubtype): Observable<CulturalOfferingSubtype> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }
  update(entity: CulturalOfferingSubtype): Observable<CulturalOfferingSubtype> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }
  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }


  getAllByTypeId(typeId: number): Observable<CulturalOfferingSubtype[]>{
    return this.apiService.get(`${this.endpoint}/byTypeId/${typeId}`);
  }
}
