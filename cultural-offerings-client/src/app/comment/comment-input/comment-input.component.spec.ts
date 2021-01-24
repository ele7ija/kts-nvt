import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { DialogModule } from 'src/app/shared/modules/dialog/dialog.module';
import { PaginatorModule } from 'src/app/shared/modules/paginator/paginator.module';
import { CommentInputComponent } from './comment-input.component';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';

describe('CommentInputComponent', () => {
  let component: CommentInputComponent;
  let fixture: ComponentFixture<CommentInputComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group').and.returnValue(
      {
        value: {},
        controls: {},
        patchValue: jasmine.createSpy('patchValue').and.returnValue({})
      })
  };

  const authServiceStub = {
    isLoggedIn: jasmine.createSpy('authServiceStub').and.returnValue(true)
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentInputComponent ],
      imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        MatProgressSpinnerModule,
        MatIconModule,
        MatChipsModule,
        MatFormFieldModule,
        MatSnackBarModule,
        PaginatorModule,
        DialogModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentInputComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.authService).toBeTruthy();
  });

  it('should call insert', () => {
    spyOn(component.commentAddedEvent, 'emit');
    component.add();
    expect(component.commentAddedEvent.emit).toHaveBeenCalled();
  });
});
