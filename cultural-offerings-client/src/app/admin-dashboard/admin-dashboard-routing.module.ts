import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CulturalOfferingPageComponent } from '../cultural-offering/cultural-offering-page/cultural-offering-page.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent
  },
  {
    path: 'cultural-offering',
    loadChildren: () => import('../cultural-offering/cultural-offering.module').then(m => m.CulturalOfferingModule)
  },
  {
    path: 'cultural-offering-type',
    loadChildren: () => import('../cultural-offering-type/cultural-offering-type.module').then(m => m.CulturalOfferingTypeModule)
  },
  {
    path: 'cultural-offering-sub-type',
    loadChildren: () => import('../cultural-offering-sub-type/cultural-offering-sub-type.module').then(m => m.CulturalOfferingSubTypeModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminDashboardRoutingModule { }
