import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PageableRequest } from '../../model/pageable-request';
import { Subscription } from '../../model/subscription';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class SubscriptionService {
  endpoint = `${environment.baseUrl}/subscriptions`;

  constructor(private apiService: ApiService) { }

  getAllEntities(): Observable<Subscription>{
    return this.apiService.get(`${this.endpoint}`);
  }

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }

  insert(entity: Subscription): Observable<Subscription> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }
  update(entity: Subscription): Observable<Subscription> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }
  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }

  getQuery(culturalOfferingId: number, userId: number): Observable<Subscription[]> {
    return this.apiService.get(
      `${this.endpoint}/all/query?culturalOfferingId=${culturalOfferingId}&userId=${userId}`
    );
  }
}
