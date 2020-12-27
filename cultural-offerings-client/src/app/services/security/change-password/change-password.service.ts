import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChangePassword } from 'src/app/model/change-password/change-password';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  private changerUrl: string = environment.baseUrl + '/users/change-user-password';

  constructor(private apiService: ApiService) { }

  sendChangePassRequest(request: ChangePassword) : Observable<any>{
    return this.apiService.put(this.changerUrl, request);
  }

}