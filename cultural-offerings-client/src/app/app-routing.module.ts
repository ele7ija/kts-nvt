import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/components/pages/not-found/not-found.component';
import { UserGuard } from './core/guards/user.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'homepage',
    pathMatch: 'full'
  }, // Don't use prefix becasue empty path is a prefix to any path
  {
    path: 'homepage',
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule),
    canActivate: [UserGuard],
    data: { allowedRoles: ['GUEST', 'USER', 'ADMIN'] }
  },
  {
    path: 'error404',
    component: NotFoundComponent
  },
  {
    path: 'sign-in',
    loadChildren: () => import('./sign-in/sign-in.module')
      .then(m => m.SignInModule), canActivate: [UserGuard], data: { allowedRoles: ['GUEST'] }
  },
  {
    path: 'register',
    loadChildren: () => import('./register/register.module')
      .then(m => m.RegisterModule), canActivate: [UserGuard], data: { allowedRoles: ['GUEST'] }
  },
  {
    path: 'my-profile',
    loadChildren: () => import('./user-data/user-data.module')
      .then(m => m.UserDataModule), canActivate: [UserGuard], data: { allowedRoles: ['USER', 'ADMIN', 'SUPER_ADMIN'] }
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin-dashboard/admin-dashboard.module')
      .then(m => m.AdminDashboardModule), canActivate: [UserGuard], data: { allowedRoles: ['ADMIN'] }
  },
  {
    path: 'cultural-offering',
    loadChildren: () => import('./cultural-offering/cultural-offering.module')
      .then(m => m.CulturalOfferingModule), canActivate: [UserGuard], data: { allowedRoles: ['GUEST', 'USER'] }
  },
  {
    path: 'super-admin',
    loadChildren: () => import('./super-admin/super-admin.module')
      .then(m => m.SuperAdminModule), canActivate: [UserGuard], data: { allowedRoles: ['SUPER_ADMIN'] }
  },
  {
    path: '**',
    redirectTo: '/error404'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
