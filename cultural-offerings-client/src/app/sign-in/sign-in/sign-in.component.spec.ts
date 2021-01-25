import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { SignInRoutingModule } from '../sign-in-routing.module';
import { SignInComponent } from './sign-in.component';
import {of, throwError} from 'rxjs';

describe('SignInComponent', () => {
  let component: SignInComponent;
  let fixture: ComponentFixture<SignInComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group3').and.returnValue({
      invalid: false,
      reset: jasmine.createSpy('reset').and.callFake(() => {}),
      value: {}
    })
  };
  const authserviceStub = {}
  const routerStub = {
    navigate: jasmine.createSpy('navigate').and.callFake(() => {})
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignInComponent ],
      imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        SignInRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: AuthService, useValue: authserviceStub},
        {provide: Router, useValue: routerStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SignInComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.router).toBeTruthy();
    expect(component.authService).toBeTruthy();
  });

  it('should attempt sign-in successfully', () => {
    authserviceStub['signin'] = jasmine.createSpy('signin').and.returnValue(of({}));
    component.signIn();
    expect(component.submitted).toBeTrue();
    expect(component.errorMsg).toBeFalsy();
    expect(routerStub.navigate).toHaveBeenCalled();
  });

  it('should attempt sign-in unsuccessfully', () => {
    authserviceStub['signin'] = jasmine.createSpy('signin').and.returnValue(throwError({}));
    component.signIn();
    expect(component.submitted).toBeFalse();
    expect(component.errorMsg).toBeTruthy();
    expect(component.signinForm.reset).toHaveBeenCalled();
  });
});
