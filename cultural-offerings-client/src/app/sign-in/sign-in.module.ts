import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { SignInComponent } from './sign-in/sign-in.component';
import { SignInService } from '../core/services/security/sign-in-service/sign-in.service';
import { SignInRoutingModule } from './sign-in-routing.module';


@NgModule({
  declarations: [
    SignInComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),

    SignInRoutingModule
  ],
  providers: [
    SignInService
  ],
  exports: [
    SignInComponent
  ]
})
export class SignInModule { }
