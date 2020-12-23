import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangePassword } from 'src/app/model/change-password/change-password';
import { ChangePasswordService } from 'src/app/services/security/change-password/change-password.service';
import { mustMatchValidator } from 'src/app/shared/validators/must-match/must-match.directive';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  passwordForm: FormGroup;

  submitted : boolean = false;
  errorMsg : string;
  successMsg : string;
  request : ChangePassword;

  constructor(private formBuilder: FormBuilder, private changePasswordService: ChangePasswordService) {
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

    this.errorMsg = undefined;
    this.successMsg = undefined;

    this.changePasswordService.sendChangePassRequest(this.request).subscribe(
      data => {
        this.successMsg = "Uspesno ste izmenili lozinku.";
      },
      error => {
        this.errorMsg = "Neuspesna izmena lozinke. Pogresno uneta trenutna lozinka.";
      }
    );

  }

}
