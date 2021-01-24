import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { RatingModule } from 'ng-starrating';
import { PaginatorModule } from 'src/app/shared/modules/paginator/paginator.module';
import { RatingListComponent } from './rating-list.component';
import {of} from 'rxjs';
import { RatingService } from 'src/app/core/services/rating/rating.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { HttpClient } from '@angular/common/http';

describe('RatingListComponent', () => {
  let component: RatingListComponent;
  let fixture: ComponentFixture<RatingListComponent>;

  const ratingServiceStub = {
    getAllByCulturalOfferingId: jasmine.createSpy('getAllByCulturalOfferingId').and.returnValue([]),
    insert: jasmine.createSpy('insert').and.returnValue(of({})),
    delete: jasmine.createSpy('insert').and.returnValue(of({}))
  };
  const authServiceStub = {
    getUserId: jasmine.createSpy('getUserId').and.returnValue(1)
  };
  const apiServiceStub = jasmine.createSpy('apiServiceStub');
  const httpClientStub = jasmine.createSpy('httpClientStub');

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingListComponent ],
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
        {provide: RatingService, useValue: ratingServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingListComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.ratingService).toBeTruthy();
    expect(component.authService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should insert rating', async () => {
    await component.ratingAddedEvent({value: 5, date: new Date()});
    expect(component.ratingService.insert).toHaveBeenCalled();
  });
});
