import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AbstractCrudService } from '../../model/abstract-crud-service';
import { CulturalOffering } from '../../model/cultural-offering';
import { PageableRequest } from '../../model/pageable-request';
import { SearchFilter } from '../../model/search-filter';
import { ApiService, RequestMethod } from '../security/api-service/api.service';

@Injectable()
export class CulturalOfferingService implements AbstractCrudService<CulturalOffering> {
  searchFilterGuest(searchFilter: SearchFilter,  pageRequest: PageableRequest) {
    return this.apiService.request(`${this.endpoint}/search-filter/by-page/guest?page=${pageRequest.page}&size=${pageRequest.size}&sort=${pageRequest.sort},${pageRequest.sortOrder}`,
      searchFilter,
      RequestMethod.Post)
  }

  endpoint: string = `${environment.baseUrl}/cultural-offerings`

  constructor(private apiService: ApiService) { }

  getAll(pageRequest: PageableRequest): Observable<any> {
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageRequest);
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

  searchFilter(searchFilter: SearchFilter, pageRequest: PageableRequest) {
    return this.apiService.request(`${this.endpoint}/search-filter/by-page?page=${pageRequest.page}&size=${pageRequest.size}&sort=${pageRequest.sort},${pageRequest.sortOrder}`,
      searchFilter,
      RequestMethod.Post)
  }
}
