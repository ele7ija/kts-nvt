import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { RegisterComponent } from './register/register.component';
import { RegisterService } from '../core/services/security/register-service/register.service';
import { RegisterRoutingModule } from './register-routing.module';
import { NavigationModule } from '../navigation/navigation.module';

@NgModule({
  declarations: [
    RegisterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NavigationModule,
    MDBBootstrapModule.forRoot(),
    
    RegisterRoutingModule
  ],
  providers: [
    RegisterService
  ]
})
export class RegisterModule { }
