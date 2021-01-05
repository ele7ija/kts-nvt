import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/components/pages/not-found/not-found.component';
import { HomepageComponent } from './shared/components/pages/homepage/homepage.component';
import { MyProfileComponent } from './user-data/my-profile/my-profile.component';
import { RegisterComponent } from './register/register/register.component';
import { SignInComponent } from './sign-in/sign-in/sign-in.component';
import { CulturalOfferingTypeComponent } from './cultural-offering-type/cultural-offering-type/cultural-offering-type.component';

const routes: Routes = [
  { path: '', redirectTo: 'homepage', pathMatch: 'full' }, // Don't use prefix becasue empty path is a prefix to any path
  { path: 'homepage', component: HomepageComponent },
  { path: 'error404', component: NotFoundComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'my-profile', component: MyProfileComponent },
  { path: 'cultural-offering-types', component: CulturalOfferingTypeComponent },
  { path: '**', redirectTo: '/error404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// avoid duplicate imports of components used in this file
export const routingComponents = [
  HomepageComponent,
  NotFoundComponent,
]