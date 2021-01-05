import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CulturalOfferingTypeComponent } from './cultural-offering-type/cultural-offering-type.component';

const routes: Routes = [
  {
    path: '',
    component: CulturalOfferingTypeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CulturalOfferingTypeRoutingModule { }