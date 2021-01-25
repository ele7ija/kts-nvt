import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { RatingModule } from 'ng-starrating';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { PaginatorModule } from 'src/app/shared/modules/paginator/paginator.module';

import { RatingInputComponent } from './rating-input.component';

describe('RatingInputComponent', () => {
  let component: RatingInputComponent;
  let fixture: ComponentFixture<RatingInputComponent>;

  const authServiceStub = {
    isLoggedIn: jasmine.createSpy('authServiceStub').and.returnValue(true)
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingInputComponent ],
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
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.authService).toBeTruthy();
  });

  it('should delegate isLoggedIn call', () => {
    component.isLoggedIn();
    expect(authServiceStub.isLoggedIn).toHaveBeenCalled();
  });

  it('should emit rate event', () => {
    spyOn(component.ratingAddedEvent, 'emit');
    component.onRate({newValue: 5, oldValue: 4});
    expect(component.ratingAddedEvent.emit).toHaveBeenCalled();
  });
});
