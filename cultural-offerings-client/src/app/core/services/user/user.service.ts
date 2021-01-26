import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AbstractCrudService } from '../../model/abstract-crud-service';
import { PageableRequest } from '../../model/pageable-request';
import { User } from '../../model/user';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class UserService implements AbstractCrudService<User> {

  private endpoint: string = environment.baseUrl + '/users';

  constructor(private apiService: ApiService) {}

  getAll(pageRequest: PageableRequest): Observable<any> {
    /*Dobavlja samo admine*/
    return this.apiService.get(`${this.endpoint}/admins/by-page`);
  }
  insert(entity: User): Observable<User> {
    return this.apiService.post(`${this.endpoint}`, entity);
  }
  update(entity: User): Observable<User> {
    return this.apiService.put(`${this.endpoint}/${entity.id}`, entity);
  }
  delete(id: number): Observable<void> {
    return this.apiService.delete(`${this.endpoint}/${id}`);
  }

  getOne(userId: number): Observable<User>{
    return this.apiService.get(`${this.endpoint}/${userId}`);
  }
}
