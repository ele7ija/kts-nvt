import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SignInUser } from 'src/app/model/sign-in-user/sign-in-user';
import { ApiService } from '../api-service/api.service';
import { catchError, map } from 'rxjs/operators';
import { SignInService } from '../sign-in-service/sign-in.service';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private access_token = null;
  private signInUrl : string = 'http://localhost:8080/api/auth/login';

  constructor(private apiService: ApiService, private signInService: SignInService, private router: Router) {}

  headers = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  });

  signin(user : SignInUser) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'email' : user.email,
      'password' : user.password
    };
    return this.apiService.post(this.signInUrl, JSON.stringify(body), loginHeaders).pipe(
      map((res) => {
        console.log('Login success');
        this.access_token = res.jwt;
        return true;
      }),
      catchError(err => {
        // Handle errors here
        console.log(err);
        return of (false);
      })
      );
  }

  logout() {
    this.signInService.changeCurrentUser(null);
    this.access_token = null;
    this.router.navigate(['/sign-in']);
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

}
