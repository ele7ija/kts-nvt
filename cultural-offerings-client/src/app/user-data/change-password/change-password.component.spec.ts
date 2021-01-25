import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NavigationModule } from 'src/app/navigation/navigation.module';
import { UserDataRoutingModule } from '../user-data-routing.module';
import { ChangePasswordComponent } from './change-password.component';
import {of, throwError} from 'rxjs';
import { ChangePasswordService } from 'src/app/core/services/security/change-password/change-password.service';

describe('ChangePasswordComponent', () => {
  let component: ChangePasswordComponent;
  let fixture: ComponentFixture<ChangePasswordComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group2').and.returnValue({
      invalid: false,
      patchValue: jasmine.createSpy('patchValue2').and.callFake(() => {}),
      value: {oldPassField: '', passField: ''},
      reset: jasmine.createSpy('reset2').and.callFake(() => {}),
    })
  };
  const changePasswordServiceStub = {
    sendChangePassRequest: jasmine.createSpy('sendChangePassRequest').and.returnValue(of({}))
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangePasswordComponent ],
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
        {provide: ChangePasswordService, useValue: changePasswordServiceStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePasswordComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.changePasswordService).toBeTruthy();
  });

  it('should attempt change password successfully', () => {
    changePasswordServiceStub.sendChangePassRequest = jasmine.createSpy('sendChangePassRequest').and.returnValue(of({}));
    component.successMsg = '';
    component.changePassword();
    expect(component.successMsg).toBeTruthy();
  });

  it('should attempt change password unsuccessfully', () => {
    changePasswordServiceStub.sendChangePassRequest = jasmine.createSpy('sendChangePassRequest').and.returnValue(throwError({}));
    component.errorMsg = '';
    component.changePassword();
    expect(component.errorMsg).toBeTruthy();
  });
});
