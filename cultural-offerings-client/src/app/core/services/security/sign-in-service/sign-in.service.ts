import { Injectable } from '@angular/core';
import { UserData } from 'src/app/core/model/current-user';

@Injectable()
export class SignInService {

  private UserData: UserData = undefined;

  constructor() { }

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
