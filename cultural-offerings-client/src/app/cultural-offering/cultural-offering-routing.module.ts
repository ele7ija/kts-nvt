import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CulturalOfferingPageComponent } from '../cultural-offering/cultural-offering-page/cultural-offering-page.component';

const routes: Routes = [
  {
    path: ':id',
    component: CulturalOfferingPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CulturalOfferingRoutingModule { }