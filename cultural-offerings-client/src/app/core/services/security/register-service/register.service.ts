import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterUser } from 'src/app/core/model/register-user';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable()
export class RegisterService {

  registrationUrl: string = environment.baseUrl + '/auth/register';

  constructor(public apiService: ApiService) { }

  sendRegistrationRequest(request: RegisterUser): Observable<any>{
    return this.apiService.post(this.registrationUrl, request);
  }

}
