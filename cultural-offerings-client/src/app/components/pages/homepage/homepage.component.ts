import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/security/auth-service/auth.service';
import { SignInService } from 'src/app/services/security/sign-in-service/sign-in.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {

  constructor(private authService: AuthService, private signInService: SignInService) { }

  ngOnInit(): void {
  }

  logoutUser(){
    this.authService.logout();
  }

}
