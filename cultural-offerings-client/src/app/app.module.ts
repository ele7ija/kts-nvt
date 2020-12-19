import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomepageComponent } from './components/pages/homepage/homepage.component';
import { ApiService } from './services/security/api-service/api.service';
import { AuthService } from './services/security/auth-service/auth.service';
import { SignInService } from './services/security/sign-in-service/sign-in.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { MustMatchDirective } from './shared/validators/must-match/must-match.directive';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    routingComponents,
    MustMatchDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    ApiService,
    AuthService,
    SignInService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
