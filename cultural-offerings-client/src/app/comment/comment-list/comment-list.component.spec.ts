import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { DialogModule } from 'src/app/shared/modules/dialog/dialog.module';
import { PaginatorModule } from 'src/app/shared/modules/paginator/paginator.module';
import { CommentListComponent } from './comment-list.component';
import {of} from 'rxjs';
import { CommentService } from 'src/app/core/services/comment/comment.service';
import { ImageService } from 'src/app/core/services/image/image.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';

describe('CommentListComponent', () => {
  let component: CommentListComponent;
  let fixture: ComponentFixture<CommentListComponent>;

  const commentServiceStub = {
    getAllByCulturalOfferingId: jasmine.createSpy('getAllByCulturalOfferingId').and.returnValue(of({contnet: [], totalElements: 0})),
    insert: jasmine.createSpy('insert').and.returnValue(of({})),
    delete: jasmine.createSpy('delete').and.returnValue(of({}))
  }
  const authServiceStub = {
    getUserId: jasmine.createSpy('authServiceStub').and.returnValue(1)
  };
  const imageServiceStub = {
    getById: function() {
      return of({});
    },
    upload: function() {
      return of({});
    }
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');
  const matSnackbarStub = {
    openFromComponent: jasmine.createSpy("matSnackbarStub").and.callFake(() => {})
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentListComponent ],
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
        {provide: CommentService, useValue: commentServiceStub},
        {provide: ImageService, useValue: imageServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatSnackBar, useValue: matSnackbarStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentListComponent);
    component = fixture.componentInstance;
    (component as any).paginator = {
      pageSize: 0
    };
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.commentService).toBeTruthy();
    expect(component.authService).toBeTruthy();
    expect(component.imageService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should attempt insert', async () => {
    await component.commentAddedEvent({date: new Date(), images: [], text: 'Something'});
    expect(component.commentService.insert).toHaveBeenCalled();
  });

  it('should attempt delete', async () => {
    await component.removeCommentEvent({id: 10, culturalOfferingId: 1, date: new Date(), imageIds: [], text: '', userId: 1});
    expect(component.commentService.delete).toHaveBeenCalledWith(10);
  });
});
