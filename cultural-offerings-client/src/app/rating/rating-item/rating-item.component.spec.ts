import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { RatingModule } from 'ng-starrating';
import { PaginatorModule } from 'src/app/shared/modules/paginator/paginator.module';
import { RatingItemComponent } from './rating-item.component';
import {of} from 'rxjs';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/core/services/user/user.service';

describe('RatingItemComponent', () => {
  let component: RatingItemComponent;
  let fixture: ComponentFixture<RatingItemComponent>;

  const userServiceStub = {
    getOne: jasmine.createSpy('authServiceStub').and.returnValue(of({}))
  };
  const authServiceStub = {
    getUserRole: jasmine.createSpy('authServiceStub').and.returnValue('ADMIN')
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingItemComponent ],
      imports: [
        CommonModule,
        RouterModule,
        PaginatorModule,
        RatingModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatSnackBarModule
      ],
      providers: [
        {provide: UserService, useValue: userServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.authService).toBeTruthy();
    expect(component.userService).toBeTruthy();
  });

  it('should delegate isUserAdmin call', () => {
    expect(component.isUserAdmin()).toBeTrue();
    expect(authServiceStub.getUserRole).toHaveBeenCalled();
  });

  it('should fetch user', async () => {
    component.rating = {userId: 1, culturalOfferingId: 1, date: new Date(), value: 5};
    await component.fetchUser();
    expect(userServiceStub.getOne).toHaveBeenCalledWith(1);
  });

  it('should emit delete event', () => {
    spyOn(component.removeRatingEvent, 'emit');
    component.delete();
    expect(component.removeRatingEvent.emit).toHaveBeenCalledWith(component.rating);
  });

});
