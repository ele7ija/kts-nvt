import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { UserService } from 'src/app/core/services/user/user.service';
import { DialogModule } from 'src/app/shared/modules/dialog/dialog.module';
import { PaginatorModule } from 'src/app/shared/modules/paginator/paginator.module';
import { CommentItemComponent } from './comment-item.component';
import {of} from 'rxjs';
import { ImageService } from 'src/app/core/services/image/image.service';

describe('CommentItemComponent', () => {
  let component: CommentItemComponent;
  let fixture: ComponentFixture<CommentItemComponent>;

  const userServiceStub = {
    getOne: jasmine.createSpy('authServiceStub').and.returnValue(of({}))
  };
  const authServiceStub = {
    getUserRole: jasmine.createSpy('authServiceStub').and.returnValue('ADMIN')
  };
  const imageServiceStub = {
    getById: function() {
      return of([]);
    },
    upload: function() {
      return of([]);
    }
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');
  const matDialogStub = jasmine.createSpy("matDialogStub");

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentItemComponent ],
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
        {provide: UserService, useValue: userServiceStub},
        {provide: ImageService, useValue: imageServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatDialog, useValue: matDialogStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentItemComponent);
    component = fixture.componentInstance;
    component.comment = {userId: 1, culturalOfferingId: 1, date: new Date(), imageIds: [], text: ''};;
    component.comment.imageIds = [];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.userService).toBeTruthy();
    expect(component.authService).toBeTruthy();
    expect(component.imageService).toBeTruthy();
    expect(component.matDialog).toBeTruthy();
  });

  it('should delegate isUserAdmin call', () => {
    expect(component.isUserAdmin()).toBeTrue();
    expect(authServiceStub.getUserRole).toHaveBeenCalled();
  });

  it('should fetch user', async () => {
    await component.fetchUser();
    expect(userServiceStub.getOne).toHaveBeenCalledWith(1);
  });

  it('should emit delete event', () => {
    spyOn(component.removeCommentEvent, 'emit');
    component.delete();
    expect(component.removeCommentEvent.emit).toHaveBeenCalledWith(component.comment);
  });
});
