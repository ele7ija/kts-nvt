import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SignInUser } from 'src/app/core/model/sign-in-user';
import { ApiService } from '../api-service/api.service';
import { catchError, map } from 'rxjs/operators';
import { SignInService } from '../sign-in-service/sign-in.service';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import jwtDecode from 'jwt-decode';

const TOKEN_KEY = 'jwt-token';
const TOKEN_KEY_PARSED = 'jwt-token-parsed';

@Injectable({
  providedIn: 'root'
})

// This service to manages token and user information inside Browser’s Session Storage.

export class AuthService {
  private signInUrl : string = environment.baseUrl + '/auth/login';

  constructor(private apiService: ApiService, private signInService: SignInService, private router: Router) {}

  signin(user : SignInUser) {
    const body = {
      'email' : user.email,
      'password' : user.password
    };
    return this.apiService.post(this.signInUrl, JSON.stringify(body)).pipe(
      map((res) => {
        console.log('Login success');
        this.saveToken(res.jwt);
        return true;
      })
    );
  }

  logout() {
    // delete all current user information by clearing Browser’s Session Storage when logout
    window.sessionStorage.clear();
    this.router.navigate(['/sign-in']);
  }

  isLoggedIn() : boolean {
    return this.getToken() ? true : false;
  }

  saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);

    window.sessionStorage.removeItem(TOKEN_KEY_PARSED);
    const decodedToken: string = JSON.stringify(jwtDecode(token));
    window.sessionStorage.setItem(TOKEN_KEY_PARSED, decodedToken);
  }

  getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  getTokenParsed(): string {
    return sessionStorage.getItem(TOKEN_KEY_PARSED);
  }

  getEmail(): string {
    return JSON.parse(sessionStorage.getItem(TOKEN_KEY_PARSED)).user.email;
  }

  

}
