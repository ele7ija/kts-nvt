import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangePassword } from 'src/app/core/model/change-password';
import { ChangePasswordService } from 'src/app/core/services/security/change-password/change-password.service';
import { mustMatchValidator } from 'src/app/shared/validators/must-match/must-match.directive';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  passwordForm: FormGroup;

  errorMsg : string;
  successMsg : string;

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

    let request : ChangePassword = {
      oldPassword: this.passwordForm.value['oldPassField'], 
      newPassword: this.passwordForm.value['passField']
    };

    this.changePasswordService.sendChangePassRequest(request).subscribe(
      data => {
        this.successMsg = "Uspesno ste izmenili lozinku.";
      },
      error => {
        this.errorMsg = "Neuspesna izmena lozinke. Pogresno uneta trenutna lozinka.";
      }
    );

  }

}
