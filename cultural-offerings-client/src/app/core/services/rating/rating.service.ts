import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { PageableRequest } from '../../model/pageable-request';
import { Rating } from '../../model/rating';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class RatingService {

  endpoint: string = `${environment.baseUrl}/rating`

  constructor(private apiService: ApiService) { }

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }

  getAllByCulturalOfferingId(pageableRequest: PageableRequest, culturalOfferingId: number) {
    return this.apiService.getByPage(`${this.endpoint}/by-page/${culturalOfferingId}`, pageableRequest);
  }

  getOne(id: string) {
    return this.apiService.get(`${this.endpoint}/${id}`);
  }

  insert(entity: Rating): Observable<Rating> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }
  update(entity: Rating): Observable<Rating> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }
  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }
}
