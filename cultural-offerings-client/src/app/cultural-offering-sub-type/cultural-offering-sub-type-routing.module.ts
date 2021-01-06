import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CulturalOfferingSubTypeComponent } from './cultural-offering-sub-type/cultural-offering-sub-type.component';

const routes: Routes = [{ path: '', component: CulturalOfferingSubTypeComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CulturalOfferingSubTypeRoutingModule { }
