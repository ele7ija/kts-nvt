import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthService) { }

  isLoggedIn : boolean = false;
  userEmail : string = "";

  ngOnInit(): void {
    console.log("called on init");
    this.fetchAllVars();
  }

  fetchAllVars() : void {
    this.authService.isLoggedIn$.subscribe(
      data => this.isLoggedIn = data
    );
    this.userEmail = this.authService.getEmail();
  }

  logout() : void {
    this.userEmail = "";
    this.authService.logout();
  }

}
