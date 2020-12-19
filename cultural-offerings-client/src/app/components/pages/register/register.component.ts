import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/security/auth-service/auth.service';
import { mustMatchValidator } from 'src/app/shared/validators/must-match/must-match.directive';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  submitted : boolean = false;
  private errorMsg : string;

  constructor(private formBuilder: FormBuilder, private router : Router,
    private authService : AuthService) {
    this.registerForm = this.formBuilder.group({
      "nameField": ["", Validators.required],
      "surnameField": ["", Validators.required],
      "emailField": ["", [Validators.required, Validators.email]],
      "passField": ["", [Validators.required, Validators.minLength(3)]],
      "confPassField": ["", [Validators.required]]
    }, { validators: mustMatchValidator });
  }

  ngOnInit(): void {

  }

  register(): void{
    // if someone tries to send invalid data do not send request
    if(this.registerForm.invalid){
      return;
    }

    // hide form controls when user send request
    this.submitted = true;
    this.errorMsg = undefined;

  }

}
