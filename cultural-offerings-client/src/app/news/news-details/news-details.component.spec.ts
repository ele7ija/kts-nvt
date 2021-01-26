import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CulturalOfferingRoutingModule } from 'src/app/cultural-offering/cultural-offering-routing.module';
import { CarouselWrapperModule } from 'src/app/shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from 'src/app/shared/modules/google-autocomplete/google-autocomplete.module';
import { NewsRoutingModule } from '../news-routing.module';
import { NewsDetailsComponent } from './news-details.component';
import {of, throwError} from 'rxjs';
import { ImageService } from 'src/app/core/services/image/image.service';
import { NewsService } from 'src/app/core/services/news/news.service';
import { AuthService } from 'src/app/core/services/security/auth-service/auth.service';
import { News } from 'src/app/core/model/news';

describe('NewsDetailsComponent', () => {
  let component: NewsDetailsComponent;
  let fixture: ComponentFixture<NewsDetailsComponent>;

  const formBuilderStub = {
    group: jasmine.createSpy('group4').and.returnValue({
      value: {},
      invalid: false
    })
  };

  const imageServiceStub = {
    getById: jasmine.createSpy('getById').and.returnValue(of({})),
    uploadAsPromise: jasmine.createSpy('uploadAsPromise').and.returnValue(Promise.resolve([{id: 1}, {id: 2}]))
  };

  const newsServiceStub = {
    getSelectedOfferingId: jasmine.createSpy('getSelectedOfferingId').and.returnValue(1),
    notify: null
  };

  const authServiceStub = {
    getUserId: jasmine.createSpy('getUserId').and.returnValue(1)
  };

  const matSnackBarStub = {
    openFromComponent: jasmine.createSpy('openFromComponent').and.callFake(() => {})
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewsDetailsComponent ],
      imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        MatSnackBarModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatIconModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        CarouselWrapperModule,
        GoogleAutocompleteModule,
        CulturalOfferingRoutingModule,
        MatTabsModule,
        NewsRoutingModule
      ],
      providers: [
        {provide: FormBuilder, useValue: formBuilderStub},
        {provide: ImageService, useValue: imageServiceStub},
        {provide: NewsService, useValue: newsServiceStub},
        {provide: AuthService, useValue: authServiceStub},
        {provide: MatSnackBar, useValue: matSnackBarStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsDetailsComponent);
    component = fixture.componentInstance;
    component.news = {id: 1, culturalOffering: 1, date: new Date(), images: [], text: '', title: '', user: 1};
    newsServiceStub.notify = jasmine.createSpy('notify').and.returnValue(of(true));
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.formBuilder).toBeTruthy();
    expect(component.imageService).toBeTruthy();
    expect(component.newsService).toBeTruthy();
    expect(component.authService).toBeTruthy();
    expect(component.matSnackBar).toBeTruthy();
  });

  it('should attempt insert successfully', async () => {
    const insertResult: News = {id: 1, culturalOffering: 1, date: new Date(), images: [], text: '', title: '', user: 1};
    spyOn(component, 'getInsertNewsPromise').and.returnValue(Promise.resolve(insertResult));
    spyOn(component.upsertLocal, 'emit');
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(insertResult);
    expect(component.newsService.notify).toHaveBeenCalledWith(1);
  });

  it('should attempt insert unsuccessfully', async () => {
    spyOn(component.upsertLocal, 'emit');
    spyOn(component, 'getInsertNewsPromise').and.returnValue(Promise.reject({error: {message: ''}}));
    await component.insert();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.news);
    expect(component.newsService.notify).toHaveBeenCalledTimes(0);
  });

  it('should attempt update successfully', async () => {
    const updateResult = {id: 1, culturalOffering: 1, date: new Date(), images: [], text: '', title: '', user: 1};
    spyOn(component, 'getUpdateNewsPromise').and.returnValue(Promise.resolve(updateResult));
    spyOn(component.upsertLocal, 'emit');
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(updateResult);
    expect(component.newsService.notify).toHaveBeenCalledWith(1);
  });

  it('should attempt update unsuccessfully', async () => {
    spyOn(component.upsertLocal, 'emit');
    spyOn(component, 'getUpdateNewsPromise').and.returnValue(Promise.reject({error: {message: ''}}));
    await component.update();
    expect(component.upsertLocal.emit).toHaveBeenCalledWith(component.news);
    expect(component.newsService.notify).toHaveBeenCalledTimes(0);
  });
});
