import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GoogleAutocompleteComponent } from './google-autocomplete/google-autocomplete.component';
import { GooglePlaceModule } from "ngx-google-places-autocomplete";


@NgModule({
  declarations: [GoogleAutocompleteComponent],
  imports: [
    CommonModule,
    GooglePlaceModule
  ],
  exports: [GoogleAutocompleteComponent]
})
export class GoogleAutocompleteModule { }
