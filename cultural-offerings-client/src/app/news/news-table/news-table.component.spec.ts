import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { ApiService } from 'src/app/core/services/security/api-service/api.service';
import { CulturalOfferingRoutingModule } from 'src/app/cultural-offering/cultural-offering-routing.module';
import { CarouselWrapperModule } from 'src/app/shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from 'src/app/shared/modules/google-autocomplete/google-autocomplete.module';
import { NewsRoutingModule } from '../news-routing.module';

import { NewsTableComponent } from './news-table.component';

describe('NewsTableComponent', () => {
  let component: NewsTableComponent;
  let fixture: ComponentFixture<NewsTableComponent>;

  const newsServiceStub = {};
  const apiServiceStub = {};
  const httpClientStub = {};
  const matSnackBarStub = {};

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewsTableComponent ],
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
        {provide: AbstractCrudService, useValue: newsServiceStub},
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub},
        {provide: MatSnackBar, useValue: matSnackBarStub},
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsTableComponent);
    component = fixture.componentInstance;
    component.ngAfterViewInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(component.newsService).toBeTruthy();
    expect(component.matSnackbar).toBeTruthy();
  });
});
