import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { mustMatchValidator } from 'src/app/shared/validators/must-match/must-match.directive';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  passwordForm: FormGroup;

  submitted : boolean = false;
  private errorMsg : string;

  constructor(private formBuilder: FormBuilder) {
    this.passwordForm = this.formBuilder.group({
      "oldPassField": ["", Validators.required],
      "passField": ["", [Validators.required, Validators.minLength(3)]],
      "confPassField": ["", [Validators.required]]
    }, { validators: mustMatchValidator });
  }

  ngOnInit(): void {

  }

  changePassword(): void{
    // if someone tries to send invalid data do not send request
    if(this.passwordForm.invalid){
      return;
    }

    // hide form controls when user send request
    this.submitted = true;
    this.errorMsg = undefined;

  }

}
