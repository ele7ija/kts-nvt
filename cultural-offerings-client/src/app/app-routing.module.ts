import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/components/pages/not-found/not-found.component';
// import { HomepageComponent } from './shared/components/pages/homepage/homepage.component';
import { MyProfileComponent } from './user-data/my-profile/my-profile.component';
import { RegisterComponent } from './register/register/register.component';
import { SignInComponent } from './sign-in/sign-in/sign-in.component';
import { AdminGuard } from './core/guards/admin.guard';
import { LoginGuard } from './core/guards/login.guard';
import { GuestGuard } from './core/guards/guest.guard';

const routes: Routes = [
  { path: '', redirectTo: 'homepage', pathMatch: 'full' }, // Don't use prefix becasue empty path is a prefix to any path
  { path: 'homepage', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
  { path: 'error404', component: NotFoundComponent },
  { path: 'sign-in', loadChildren: () => import('./sign-in/sign-in.module').then(m => m.SignInModule), canActivate: [GuestGuard]},
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule), canActivate: [GuestGuard]},
  { path: 'my-profile', loadChildren: () => import('./user-data/user-data.module').then(m => m.UserDataModule), canActivate: [LoginGuard]},
  { path: 'cultural-offering-types', loadChildren: () => import('./cultural-offering-type/cultural-offering-type.module').then(m => m.CulturalOfferingTypeModule), canActivate: [AdminGuard]},
  { path: 'cultural-offering-sub-type', loadChildren: () => import('./cultural-offering-sub-type/cultural-offering-sub-type.module').then(m => m.CulturalOfferingSubTypeModule), canActivate: [AdminGuard] },
  { path: '**', redirectTo: '/error404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }