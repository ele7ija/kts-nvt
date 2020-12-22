import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-change-user-data',
  templateUrl: './change-user-data.component.html',
  styleUrls: ['./change-user-data.component.scss']
})
export class ChangeUserDataComponent implements OnInit {

  userDataForm: FormGroup;

  submitted : boolean = false;
  private errorMsg : string;
  user;

  constructor(private formBuilder: FormBuilder) {
    this.userDataForm = this.formBuilder.group({
      "nameField": ["", Validators.required],
      "surnameField": ["", Validators.required],
    });
  }

  ngOnInit(): void {

  }

  changeUserData(): void{
    // if someone tries to send invalid data do not send request
    if(this.userDataForm.invalid){
      return;
    }

    // hide form controls when user send request
    this.submitted = true;
    this.errorMsg = undefined;

  }

}
