import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserTableComponent } from './user-table/user-table.component';
import { SuperAdminRoutingModule } from './super-admin-routing.module';



@NgModule({
  declarations: [UserTableComponent],
  imports: [
    CommonModule,
    SuperAdminRoutingModule
  ]
})
export class SuperAdminModule { }
