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
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { CultrualOfferingTypeDetailsComponent } from './components/forms/cultrual-offering-type-details/cultrual-offering-type-details.component';
import { CulturalOfferingSubtypeChipsComponent } from './components/forms/cultural-offering-subtype-chips/cultural-offering-subtype-chips.component';
import { SimpleSnackbarComponent } from './components/snackbar/simple-snackbar/simple-snackbar.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    MustMatchDirective,
    ChangePasswordComponent,
    ChangeUserDataComponent,
    CulturalOfferingTypeComponent,
    CultrualOfferingTypeDetailsComponent,
    CulturalOfferingSubtypeChipsComponent,
    SimpleSnackbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    
    //material design components
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatChipsModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
