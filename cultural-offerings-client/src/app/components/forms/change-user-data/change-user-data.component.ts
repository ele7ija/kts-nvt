import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserData } from 'src/app/model/current-user/current-user';
import { ChangeUserDataService } from 'src/app/services/security/change-user-data/change-user-data.service';

@Component({
  selector: 'app-change-user-data',
  templateUrl: './change-user-data.component.html',
  styleUrls: ['./change-user-data.component.scss']
})
export class ChangeUserDataComponent implements OnInit {

  userDataForm: FormGroup;

  errorMsg : string;
  successMsg: string;
  user : UserData = new UserData("", "");
  initialFormValues;

  constructor(private formBuilder: FormBuilder, private changeUserDataService : ChangeUserDataService) {
    this.userDataForm = this.formBuilder.group({
      "nameField": [this.user.firstName, Validators.required],
      "surnameField": [this.user.lastName, Validators.required],
    });
  }

  ngOnInit(): void {
    this.prefillForm();
  }

  prefillForm() {
    this.changeUserDataService.getDataRequest().subscribe(
      data => {
        this.user.firstName = data.firstName;
        this.user.lastName = data.lastName;
        this.initialFormValues = this.userDataForm.value;
      },
      error => {
        this.errorMsg = "Greska prilikom dobavljanja podataka. Molimo Vas pokusajte izmenu maalo kasnije.";
      }
    );
  }

  changeUserData(): void{
    // if someone tries to send invalid data do not send request
    if(this.userDataForm.invalid){
      return;
    }

    this.errorMsg = undefined;
    this.successMsg = undefined;

    this.changeUserDataService.changeDataRequest(this.user).subscribe(
      data => {
        this.successMsg = "Uspesno ste izmenili Vase podatke.";
        this.initialFormValues = this.userDataForm.value;
      },
      error => {
        this.errorMsg = "Neuspesna izmena podataka. Uneli ste nevalidan tip podataka.";
        this.userDataForm.reset(this.initialFormValues);
      }
    );

  }

}
