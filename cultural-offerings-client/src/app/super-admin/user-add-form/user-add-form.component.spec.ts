import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MDBBootstrapModule, TableModule } from 'angular-bootstrap-md';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { UserService } from 'src/app/core/services/user/user.service';
import { SuperAdminRoutingModule } from '../super-admin-routing.module';
import { UserAddFormComponent } from './user-add-form.component';
import {of} from 'rxjs';

describe('UserAddFormComponent', () => {
  let component: UserAddFormComponent;
  let fixture: ComponentFixture<UserAddFormComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group').and.returnValue({value: {}})
  };
  const snackbarStub = jasmine.createSpy('snackbarStub');
  const userServiceStub = jasmine.createSpyObj('userServiceStub', ['insert']);
  userServiceStub.insert.and.returnValue(of({}));
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAddFormComponent ],
      imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        MatIconModule,
        MatChipsModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        TableModule,
        
        SuperAdminRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: UserService, useValue: userServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatSnackBar, useValue: snackbarStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddFormComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.userService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should attempt insert', async () => {
    await component.getInsertUserPromise();
    expect(userServiceStub.insert).toHaveBeenCalled();
  });

  it('should fail insert', async () => {
    spyOn(component, 'getInsertUserPromise').and.returnValue(Promise.reject({error: {message: ''}}));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledTimes(0);
  });

  it('should pass insert', async () => {
    const param = {id: 1, firstName: '', lastName: '', email: ''};
    spyOn(component, 'getInsertUserPromise').and.returnValue(Promise.resolve(param));
    spyOn(component.upsertLocal, 'emit').and.callThrough();
    spyOn(component, 'showSnackbar').and.callFake(() => {});
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(param);
  });
});
