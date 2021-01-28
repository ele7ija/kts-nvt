import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SignInUser } from 'src/app/core/model/sign-in-user';
import { ApiService } from '../api-service/api.service';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import jwtDecode from 'jwt-decode';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';

const TOKEN_KEY = 'jwt-token';
const TOKEN_KEY_PARSED = 'jwt-token-parsed';

@Injectable({
  providedIn: 'root'
})

// This service to manages token and user information inside Browser’s Session Storage.

export class AuthService {

  signInUrl: string = environment.baseUrl + '/auth/login';

  constructor(public apiService: ApiService, public router: Router, public location: Location) { }

  signin(user: SignInUser): Observable<any> {
    const body = {
      email: user.email,
      password: user.password
    };
    return this.apiService.post(this.signInUrl, JSON.stringify(body)).pipe(
      map((res) => {
        this.saveToken(res.jwt);
        return true;
      })
    );
  }

  logout(): void {
    // delete all current user information by clearing Browser’s Session Storage when logout
    window.sessionStorage.clear();
  }

  isLoggedIn(): boolean {
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
    if (this.isLoggedIn()) {
      return JSON.parse(sessionStorage.getItem(TOKEN_KEY_PARSED)).user.email;
    }
    return '';
  }

  getUserRole(): string {
    if (this.isLoggedIn()) {
      return JSON.parse(sessionStorage.getItem(TOKEN_KEY_PARSED)).user.userRole;
    }
    return '';
  }

  getUserId(): number {
    if (this.isLoggedIn()) {
      return JSON.parse(sessionStorage.getItem(TOKEN_KEY_PARSED)).user.id;
    }
    return -1;
  }

  navigateUnauthorized(): void {
    if (this.getUserRole() == 'ADMIN' || this.getUserRole() == 'USER') {
      this.router.navigate(['homepage']);
    }
    else if (this.getUserRole() == 'SUPER_ADMIN') {
      this.router.navigate(['super-admin/admins']);
    }
    else {
      this.router.navigate(['error404']);
    }
  }

}
