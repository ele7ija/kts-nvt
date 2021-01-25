import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { RegisterService } from 'src/app/core/services/security/register-service/register.service';
import { NavigationModule } from 'src/app/navigation/navigation.module';
import { RegisterRoutingModule } from '../register-routing.module';
import { RegisterComponent } from './register.component';
import {of, throwError} from 'rxjs';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group3').and.returnValue({
      invalid: false,
      reset: jasmine.createSpy('reset').and.callFake(() => {}),
      value: {
        emailField: 'pera.pera@gmail.com',
        nameField: 'Pera',
        surnameField: 'Peric',
        passField: 'adqweqweqwe'
      }
    })
  };
  const registerServiceStub = {};

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
      imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        NavigationModule,
        MDBBootstrapModule.forRoot(),
        RegisterRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: RegisterService, useValue: registerServiceStub},
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.registerService).toBeTruthy();
  });

  it('should attempt registration successfully', () => {
    registerServiceStub['sendRegistrationRequest'] = jasmine.createSpy('signin').and.returnValue(of({}));
    component.errorMsg = '';
    component.successMsg = '';
    component.register();
    expect(component.submitted).toBeTrue();
    expect(component.errorMsg).toBeFalsy();
    expect(component.successMsg).toBeTruthy();
  });

  it('should attempt registration unsuccessfully', () => {
    registerServiceStub['sendRegistrationRequest'] = jasmine.createSpy('signin').and.returnValue(throwError({}));
    component.errorMsg = '';
    component.successMsg = '';
    component.register();
    expect(component.submitted).toBeFalse();
    expect(component.errorMsg).toBeTruthy();
    expect(component.successMsg).toBeFalsy();
  });
});
