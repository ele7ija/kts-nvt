import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SignInUser } from 'src/app/model/sign-in-user/sign-in-user';
import { ApiService } from '../api-service/api.service';
import { catchError, map } from 'rxjs/operators';
import { SignInService } from '../sign-in-service/sign-in.service';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

const TOKEN_KEY = 'jwt-token';
const EMAIL_KEY = 'user-email';
const ROLE_KEY = 'user-roles';

@Injectable({
  providedIn: 'root'
})

// This service to manages token and user information inside Browser’s Session Storage.

export class AuthService {
  private signInUrl : string = environment.baseUrl + '/auth/login';

  constructor(private apiService: ApiService, private signInService: SignInService, private router: Router) {}

  headers = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  });

  signin(user : SignInUser) {
    const body = {
      'email' : user.email,
      'password' : user.password
    };
    return this.apiService.post(this.signInUrl, JSON.stringify(body), this.headers).pipe(
      map((res) => {
        console.log('Login success');
        this.saveToken(res.jwt);
        this.saveEmail(user.email);
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
  }

  getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  saveEmail(email: string): void {
    window.sessionStorage.removeItem(EMAIL_KEY);
    window.sessionStorage.setItem(EMAIL_KEY, email);
  }

  getEmail(): string {
    return sessionStorage.getItem(EMAIL_KEY);
  }

}
