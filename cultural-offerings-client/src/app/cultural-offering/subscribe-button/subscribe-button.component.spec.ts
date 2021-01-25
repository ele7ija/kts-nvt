import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule } from '@angular/router';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommentModule } from 'src/app/comment/comment.module';
import { RatingsModule } from 'src/app/rating/rating.module';
import { CarouselWrapperModule } from 'src/app/shared/modules/carousel/carousel.module';
import { GoogleAutocompleteModule } from 'src/app/shared/modules/google-autocomplete/google-autocomplete.module';
import { TableModule } from 'src/app/shared/modules/table/table.module';
import { CulturalOfferingRoutingModule } from '../cultural-offering-routing.module';

import { SubscribeButtonComponent } from './subscribe-button.component';

describe('SubscribeButtonComponent', () => {
  let component: SubscribeButtonComponent;
  let fixture: ComponentFixture<SubscribeButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribeButtonComponent ],
      imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        MDBBootstrapModule.forRoot(),
        TableModule,
        MatIconModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        CarouselWrapperModule,
        GoogleAutocompleteModule,
        CulturalOfferingRoutingModule,
        MatTabsModule,
        CommentModule,
        RatingsModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribeButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
