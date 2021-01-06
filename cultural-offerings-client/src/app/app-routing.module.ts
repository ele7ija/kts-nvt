import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/components/pages/not-found/not-found.component';
import { HomepageComponent } from './shared/components/pages/homepage/homepage.component';
import { MyProfileComponent } from './user-data/my-profile/my-profile.component';
import { RegisterComponent } from './register/register/register.component';
import { SignInComponent } from './sign-in/sign-in/sign-in.component';

const routes: Routes = [
  { path: '', redirectTo: 'homepage', pathMatch: 'full' }, // Don't use prefix becasue empty path is a prefix to any path
  { path: 'homepage', component: HomepageComponent },
  { path: 'error404', component: NotFoundComponent },
  { path: 'sign-in', loadChildren: () => import('./sign-in/sign-in.module').then(m => m.SignInModule)},
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule)},
  { path: 'my-profile', loadChildren: () => import('./user-data/user-data.module').then(m => m.UserDataModule)},
  { path: 'cultural-offering-types', loadChildren: () => import('./cultural-offering-type/cultural-offering-type.module').then(m => m.CulturalOfferingTypeModule)},
  { path: 'cultural-offering-sub-type', loadChildren: () => import('./cultural-offering-sub-type/cultural-offering-sub-type.module').then(m => m.CulturalOfferingSubTypeModule) },
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