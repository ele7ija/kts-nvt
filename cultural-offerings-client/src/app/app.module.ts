import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from './services/security/api-service/api.service';
import { AuthService } from './services/security/auth-service/auth.service';
import { SignInService } from './services/security/sign-in-service/sign-in.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { MustMatchDirective } from './shared/validators/must-match/must-match.directive';
import { ChangePasswordComponent } from './components/forms/change-password/change-password.component';
import { ChangeUserDataComponent } from './components/forms/change-user-data/change-user-data.component';
import { MyProfileComponent } from './components/pages/my-profile/my-profile.component';
import { CulturalOfferingTypeComponent } from './components/pages/cultural-offering-type/cultural-offering-type.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    MustMatchDirective,
    ChangePasswordComponent,
    ChangeUserDataComponent,
    CulturalOfferingTypeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    MatProgressSpinnerModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
