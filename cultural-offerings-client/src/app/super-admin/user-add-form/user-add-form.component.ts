import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/core/model/user';
import { UserService } from 'src/app/core/services/user/user.service';
import { SimpleSnackbarComponent } from 'src/app/shared/components/snackbar/simple-snackbar/simple-snackbar.component';

@Component({
  selector: 'app-user-add-form',
  templateUrl: './user-add-form.component.html',
  styleUrls: ['./user-add-form.component.scss']
})
export class UserAddFormComponent implements OnInit {

  @Output()
  upsertLocal: EventEmitter<User> = new EventEmitter<User>();

  loading = false;
  userForm: FormGroup;
  submitAttempted = false;

  constructor(
    public formBuilder: FormBuilder,
    public userService: UserService,
    public matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
    });
  }

  isFormInvalid(): any{
    return Object.values(this.userForm.controls).find(control => control.errors);
  }

  async getInsertUserPromise(): Promise<User>{
    const user: User = {
      id: null,
      email: this.userForm.value.email,
      password: this.userForm.value.password,
      firstName: this.userForm.value.firstName,
      lastName: this.userForm.value.lastName,
      userRole: 'ADMIN',
      commentIds: [],
      ratingIds: [],
      newsIds: [],
      subscriptionIds: []
    };
    return this.userService.insert(user).toPromise();
  }

  upsert(): void{
    this.submitAttempted = true;
    if (this.isFormInvalid()) {
      return;
    }
    this.insert();
  }

  async insert(): Promise<void>{
    this.loading = true;
    try {
      const insertedUser = await this.getInsertUserPromise();
      this.upsertLocal.emit(insertedUser);
      this.showSnackbar('USPESNO DODAVANJE', `Korisnik sa email-om ${insertedUser.email} je uspesno dodat`, true);
    } catch ({error}) {
      // show toast
      this.showSnackbar('NEUSPESNO DODAVANJE', `${error.message}`, false);
    }
    this.loading = false;
  }

  showSnackbar(title: string, message: string, success: boolean): void {
    this.matSnackBar.openFromComponent(SimpleSnackbarComponent, {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 4000,
      data: {
        title,
        message,
        success,
      },
    });
  }

}
