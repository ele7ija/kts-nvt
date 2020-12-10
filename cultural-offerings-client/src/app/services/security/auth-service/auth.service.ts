import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // add private _loginService: LoginService
  constructor(private http: HttpClient, private _apiService: ApiService, private _router: Router) {}

  headers = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  });

  private access_token = null;
  //private apiService: ApiService;

  /*
  login(user) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'username' : user.username,
      'password' : user.password
    };
    return this._apiService.post('http://localhost:8080/auth/login', JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        console.log('Login success');
        this.access_token = res.accessToken;
      }));
  }
  */

  /*
  logout() {
    this._loginService.currentUser = null;
    this.access_token = null;
    this._router.navigate(['/login']);
  }
  */

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

}
