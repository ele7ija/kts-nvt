import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AbstractCrudService } from '../../model/abstract-crud-service';
import { News } from '../../model/news';
import { PageableRequest } from '../../model/pageable-request';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class NewsService implements AbstractCrudService<News>{

  endpoint: string = `${environment.baseUrl}/news`

  constructor(private apiService: ApiService) { }

  getAll(pageRequest: PageableRequest): Observable<any> {
    return this.apiService.get(`${this.endpoint}/all/by-page/${pageRequest.page}/${pageRequest.size}`);
  }

  getOne(id: string) {
    return this.apiService.get(`${this.endpoint}/${id}`);
  }

  insert(entity: News): Observable<News> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }

  update(entity: News): Observable<News> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }

  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }

  notify(id: number): Observable<Boolean> {
    return this.apiService.post(`${this.endpoint}/notify/${id}`);
  }
}
