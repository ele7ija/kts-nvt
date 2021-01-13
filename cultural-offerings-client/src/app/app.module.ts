import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './core/interceptor/token.interceptor';
import { MustMatchDirective } from './shared/validators/must-match/must-match.directive';
import { NavigationModule } from './navigation/navigation.module';
import { AdminGuard } from './core/guards/admin.guard';
import { GuestGuard } from './core/guards/guest.guard';
import { LoginGuard } from './core/guards/login.guard';
import { SuperAdminGuard } from './core/guards/super-admin.guard';
import { UserGuard } from './core/guards/user.guard';

@NgModule({
  declarations: [
    AppComponent,
    MustMatchDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NavigationModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    AdminGuard,
    GuestGuard,
    LoginGuard,
    SuperAdminGuard,
    UserGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
