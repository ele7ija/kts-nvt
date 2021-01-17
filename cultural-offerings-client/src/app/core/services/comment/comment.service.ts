import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../../model/comment';
import { PageableRequest } from '../../model/pageable-request';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class CommentService {

  endpoint: string = `${environment.baseUrl}/comments`

  constructor(private apiService: ApiService) { }

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }

  getAllByCulturalOfferingId(pageableRequest: PageableRequest, culturalOfferingId: number): Observable<any> {
    return this.apiService.getByPage(`${this.endpoint}/by-page/${culturalOfferingId}`, pageableRequest);
  }

  getOne(id: string) {
    return this.apiService.get(`${this.endpoint}/${id}`);
  }

  insert(entity: Comment): Observable<Comment> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }
  update(entity: Comment): Observable<Comment> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }
  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }
}
