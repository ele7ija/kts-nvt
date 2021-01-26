import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserData } from 'src/app/core/model/current-user';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable()
export class ChangeUserDataService {

  private changerUrl: string = environment.baseUrl + '/users/change-user-data';
  private getUrl: string = environment.baseUrl + '/users/get-user-data';

  constructor(private apiService: ApiService) { }

  // get personal data of logged-in user
  getDataRequest(): Observable<any>{
    return this.apiService.get(this.getUrl);
  }

  changeDataRequest(request: UserData): Observable<any>{
    return this.apiService.put(this.changerUrl, request);
  }

}
