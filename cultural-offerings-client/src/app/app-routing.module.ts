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
import { UserGuard } from './core/guards/user.guard';

const routes: Routes = [
  { path: '', redirectTo: 'homepage', pathMatch: 'full' }, // Don't use prefix becasue empty path is a prefix to any path
  { path: 'homepage', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
  { path: 'error404', component: NotFoundComponent },
  { path: 'sign-in', loadChildren: () => import('./sign-in/sign-in.module').then(m => m.SignInModule), canActivate: [GuestGuard]},
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule), canActivate: [GuestGuard]},
  { path: 'my-profile', loadChildren: () => import('./user-data/user-data.module').then(m => m.UserDataModule), canActivate: [LoginGuard]},
  { path: 'admin', loadChildren: () => import('./admin-dashboard/admin-dashboard.module').then(m => m.AdminDashboardModule), canActivate: [AdminGuard] },
  { path: 'cultural-offering', loadChildren: () => import('./cultural-offering/cultural-offering.module').then(m => m.CulturalOfferingModule) },
  { path: '**', redirectTo: '/error404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }