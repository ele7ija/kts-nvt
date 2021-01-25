import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { NavigationModule } from 'src/app/navigation/navigation.module';
import { UserDataRoutingModule } from '../user-data-routing.module';
import { ChangeUserDataComponent } from './change-user-data.component';
import {of, throwError} from 'rxjs';
import { ChangeUserDataService } from 'src/app/core/services/security/change-user-data/change-user-data.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';

describe('ChangeUserDataComponent', () => {
  let component: ChangeUserDataComponent;
  let fixture: ComponentFixture<ChangeUserDataComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group').and.returnValue({
      invalid: false,
      patchValue: jasmine.createSpy('patchValue').and.callFake(() => {}),
      value: {},
      reset: jasmine.createSpy('reset').and.callFake(() => {}),
    })
  };
  const changeUserDataServiceStub = {
    getDataRequest: jasmine.createSpy('getDataRequest').and.returnValue(of({})),
    changeDataRequest: jasmine.createSpy('changeDataRequest').and.returnValue(of({}))
  }
  const authServiceStub = {
    getEmail: jasmine.createSpy('getEmail').and.returnValue('pera.pera@gmail.com')
  };
  const apiServiceStub =  jasmine.createSpy('apiServiceStub');
  const httpClientStub =  jasmine.createSpy('httpClientStub'); 

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeUserDataComponent ],
      imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        NavigationModule,
        MDBBootstrapModule.forRoot(),
        UserDataRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: ChangeUserDataService, useValue: changeUserDataServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeUserDataComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.authService).toBeTruthy();
    expect(component.formBuilder).toBeTruthy();
    expect(component.changeUserDataService).toBeTruthy();
  });

  it('should prefill form successfully', () => {
    changeUserDataServiceStub.getDataRequest = jasmine.createSpy('getDataRequest').and.returnValue(of({}));
    component.prefillForm();
    expect(component.user.email).toEqual('pera.pera@gmail.com');
    expect(component.userDataForm.patchValue).toHaveBeenCalled();
  });

  it('should prefill form unsuccessfully', () => {
    changeUserDataServiceStub.getDataRequest = jasmine.createSpy('getDataRequest').and.returnValue(throwError(''));
    component.errorMsg = '';
    component.prefillForm();
    expect(component.errorMsg).toBeTruthy();
  });

  it('should change user data successfully', () => {
    changeUserDataServiceStub.changeDataRequest = jasmine.createSpy('changeDataRequest').and.returnValue(of({}));
    component.successMsg = '';
    component.changeUserData();
    expect(component.successMsg).toBeTruthy();
  });

  it('should change user data unsuccessfully', () => {
    changeUserDataServiceStub.changeDataRequest = jasmine.createSpy('changeDataRequest').and.returnValue(throwError(''));
    component.errorMsg = '';
    component.changeUserData();
    expect(component.errorMsg).toBeTruthy();
    expect(component.userDataForm.reset).toHaveBeenCalled();
  });
});
