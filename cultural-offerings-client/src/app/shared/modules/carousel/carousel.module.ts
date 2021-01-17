import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarouselComponent } from './carousel/carousel.component';
import { CarouselModule, WavesModule } from 'angular-bootstrap-md';

@NgModule({
  declarations: [CarouselComponent],
  imports: [
    CommonModule,
    CarouselModule,
    WavesModule
  ],
  exports: [CarouselComponent]
})
export class CarouselWrapperModule { }
