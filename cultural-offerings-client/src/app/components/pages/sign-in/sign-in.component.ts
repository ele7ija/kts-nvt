import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  signinForm: FormGroup;
  private emailField: string;
  private passField: string;

  constructor(private formBuilder: FormBuilder) {
    this.signinForm = this.formBuilder.group({
      "emailField": ["", [Validators.required, Validators.email]],
      "passField": ["", [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit(): void {

  }

  signIn(): void{
    console.log(this.signinForm.value['emailField'] +" "+ this.signinForm.value['passField']);
  }

}
