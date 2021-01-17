import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminDashboardRoutingModule } from './admin-dashboard-routing.module';
import { CulturalOfferingModule } from '../cultural-offering/cultural-offering.module';

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    AdminDashboardRoutingModule,
    CulturalOfferingModule
  ]
})
export class AdminDashboardModule { }
