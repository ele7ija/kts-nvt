import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/security/auth-service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(public router: Router, public authService: AuthService) {
  }

  // only users with role USER can activate
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const allowedRoles = route.data['allowedRoles'];
      console.log(allowedRoles);
      if (this.authService.isLoggedIn()) {
        if(allowedRoles.filter(role => role == this.authService.getUserRole()).length == 1){
          return true;
        }else{
          this.authService.navigateUnauthorized();
          return false;
        }
      }else{
        if(allowedRoles.find(role => role == 'GUEST')){
          return true;
        }else{
          this.authService.navigateUnauthorized();
          return false; 
        }
      }
  }
  
}
