import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignInUser } from 'src/app/core/model/sign-in-user';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { SignInService } from 'src/app/core/services/security/sign-in-service/sign-in.service';
import { of } from 'rxjs';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  signinForm: FormGroup;
  private emailField: string;
  private passField: string;

  submitted : boolean = false;
  private errorMsg : string;

  private user : SignInUser;

  constructor(private formBuilder: FormBuilder, private router : Router,
    private authService : AuthService, private signInService : SignInService) {
    this.signinForm = this.formBuilder.group({
      "emailField": ["", [Validators.required, Validators.email]],
      "passField": ["", [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit(): void {

  }

  signIn(): void{
    // if someone tries to send invalid data do not send request
    if(this.signinForm.invalid){
      return;
    }

    // hide form controls when user send request
    this.submitted = true;
    this.errorMsg = undefined;

    this.user = {
      email: this.signinForm.value['emailField'],
      password: this.signinForm.value['passField']
    };

    this.authService.signin(this.user).subscribe(
      data => {
        this.router.navigate(['/homepage']);
        console.log(`Korisnik ${this.user.email} je uspesno pristupio sistemu.`);
      },
      error => {
        this.submitted = false;
        this.signinForm.reset();
        this.errorMsg = 'Uneli ste pogresnu email adresu ili lozinku.';
      });

  }

}
