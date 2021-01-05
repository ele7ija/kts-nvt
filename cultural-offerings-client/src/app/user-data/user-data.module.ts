import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { ChangePasswordComponent } from './change-password/change-password.component';
import { ChangeUserDataComponent } from './change-user-data/change-user-data.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { ChangePasswordService } from '../core/services/security/change-password/change-password.service';
import { ChangeUserDataService } from '../core/services/security/change-user-data/change-user-data.service';
import { UserDataRoutingModule } from './user-data-routing.module';

@NgModule({
  declarations: [
    ChangePasswordComponent,
    ChangeUserDataComponent,
    MyProfileComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),

    UserDataRoutingModule
  ],
  providers: [
    ChangePasswordService,
    ChangeUserDataService,

  ],
  exports: [
    MyProfileComponent
  ]
})
export class UserDataModule { }
