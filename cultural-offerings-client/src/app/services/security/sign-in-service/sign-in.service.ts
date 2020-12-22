import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { UserData } from 'src/app/model/current-user/current-user';
import { SignInUser } from 'src/app/model/sign-in-user/sign-in-user';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  private UserData: UserData = undefined;

  constructor(private _apiService: ApiService) { }

  // was setupuser method
  changeUserData(user : UserData) : void {
    this.UserData = user;
  }

  getUserData() : UserData {
    return this.UserData;
  }

  updateUserData(name: string, surname: string) : void{
    this.UserData.firstName = name;
    this.UserData.lastName = surname;
  }

}
