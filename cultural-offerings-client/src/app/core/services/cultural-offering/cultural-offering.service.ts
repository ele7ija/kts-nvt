import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { AbstractCrudService } from '../../model/abstract-crud-service';
import { CulturalOffering } from '../../model/cultural-offering';
import { PageableRequest } from '../../model/pageable-request';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class CulturalOfferingService implements AbstractCrudService<CulturalOffering> {

  endpoint: string = `${environment.baseUrl}/cultural-offerings`

  constructor(private apiService: ApiService) { }

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }

  getOne(id: string) {
    return this.apiService.get(`${this.endpoint}/${id}`);
  }

  insert(entity: CulturalOffering): Observable<CulturalOffering> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }
  update(entity: CulturalOffering): Observable<CulturalOffering> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }
  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }
}
