import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { CurrentUser } from 'src/app/model/current-user/current-user';
import { SignInUser } from 'src/app/model/sign-in-user/sign-in-user';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  // private _getUserInfoUrl = "http://localhost:8080/api/users/returnCurrentUser";
  //private _refresh_token_url = "http://localhost:8080/auth/refresh";

  private currentUser: CurrentUser;

  constructor(private _apiService: ApiService) { }

  /*
  public getMyInfo() {
    return this._apiService.get(this._getUserInfoUrl)
      .pipe(map(user => {
        this.currentUser = user;
        return user;
      }));
  }
  */

  /*
  initUser() {
    const promise = this._apiService.get(this._refresh_token_url).toPromise()
      .then(res => {
        if (res.access_token !== null) {
          return this.getMyInfo().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }
  */

  // was setupuser method
  changeCurrentUser(user : CurrentUser) : void {
    this.currentUser = user;
  }

  getCurrentUser() : CurrentUser {
    return this.currentUser;
  }

  updateCurrentUser(name: string, surname: string) : void{
    this.currentUser.firstName = name;
    this.currentUser.lastName = surname;
  }

  setUser(email: string, password: string) : SignInUser {
    return new SignInUser(email, password);
  }


}
