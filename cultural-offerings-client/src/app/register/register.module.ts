import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { RegisterComponent } from './register/register.component';
import { RegisterService } from '../core/services/security/register-service/register.service';
import { RegisterRoutingModule } from './register-routing.module';

@NgModule({
  declarations: [
    RegisterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    
    RegisterRoutingModule
  ],
  providers: [
    RegisterService
  ],
  exports: [
    RegisterComponent
  ]
})
export class RegisterModule { }
