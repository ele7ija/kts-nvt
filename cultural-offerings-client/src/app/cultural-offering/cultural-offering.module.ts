import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CulturalOfferingTableComponent } from './cultural-offering-table/cultural-offering-table.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CulturalOfferingDetailsComponent } from './cultural-offering-details/cultural-offering-details.component';
import { CarouselWrapperModule } from '../shared/modules/carousel/carousel.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CulturalOfferingService } from '../core/services/cultural-offering/cultural-offering.service';
import { CulturalOfferingSubtypeService } from '../core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from '../core/services/cultural-offering-type/cultural-offering-type.service';
import { ImageService } from '../core/services/image/image.service';
import { GoogleAutocompleteModule } from '../shared/modules/google-autocomplete/google-autocomplete.module';
import { CulturalOfferingPageComponent } from './cultural-offering-page/cultural-offering-page.component';
import { RouterModule } from '@angular/router';
import { CulturalOfferingRoutingModule } from './cultural-offering-routing.module';
import { CommentModule } from '../comment/comment.module';
import { RatingsModule } from '../rating/rating.module';
import { MatTabsModule } from '@angular/material/tabs';
import { SubscribeButtonComponent } from './subscribe-button/subscribe-button.component';
import { SubscriptionService } from '../core/services/subscription/subscription.service';
import { AuthService } from '../core/services/security/auth-service/auth.service';
import { TableModule } from '../shared/modules/table/table.module';

@NgModule({
  declarations: [
    CulturalOfferingTableComponent,
    CulturalOfferingDetailsComponent,
    CulturalOfferingPageComponent,
    SubscribeButtonComponent
  ],
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
  ],
  providers: [
    CulturalOfferingService,
    CulturalOfferingTypeService,
    CulturalOfferingSubtypeService,
    ImageService,
    SubscriptionService,
    AuthService
  ],
  exports: [
    CulturalOfferingTableComponent
  ]
})
export class CulturalOfferingModule { }
