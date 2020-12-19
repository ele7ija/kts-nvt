import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterUser } from 'src/app/model/register-user/register-user';
import { environment } from 'src/environments/environment';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private registrationUrl: string = environment.baseUrl + '/auth/register';

  constructor(private apiService: ApiService) { }

  sendRegistrationRequest(request: RegisterUser) : Observable<any>{
    return this.apiService.post(this.registrationUrl, request, this.apiService.headers);
  }

}
