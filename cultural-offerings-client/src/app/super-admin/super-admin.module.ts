import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserTableComponent } from './user-table/user-table.component';
import { SuperAdminRoutingModule } from './super-admin-routing.module';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TableModule } from '../shared/modules/table/table.module';
import { UserAddFormComponent } from './user-add-form/user-add-form.component';
import { UserService } from '../core/services/user/user.service';

@NgModule({
  declarations: [UserTableComponent, UserAddFormComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    MatIconModule,
    MatChipsModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    TableModule,
    SuperAdminRoutingModule
  ],
  providers: [
    UserService
  ]
})
export class SuperAdminModule { }
