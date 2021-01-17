import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../../model/user';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class UserService {

  private endpoint: string = environment.baseUrl + '/users'

  constructor(private apiService: ApiService) {}

  getOne(userId: number): Observable<User>{
    return this.apiService.get(`${this.endpoint}/${userId}`);
  }
}
