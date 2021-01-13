import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/security/auth-service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SuperAdminGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) {
  }

  // only users with role SUPER_ADMIN can activate
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (this.authService.isLoggedIn()) {
        if (this.authService.getUserRole() === 'SUPER_ADMIN') {
          return true;
        } else {
          //if other role
          this.authService.navigateUnauthorized();
          return false;
        }
      }
      else {
        //current user does not exist - no one is logged in
        this.router.navigate(['/sign-in']);
        return false;
      }
  }
  
}
