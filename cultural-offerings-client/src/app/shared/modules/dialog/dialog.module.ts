import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import { CarouselDialogComponent } from './carousel-dialog/carousel-dialog.component';
import { CarouselWrapperModule } from '../carousel/carousel.module';


@NgModule({
  declarations: [CarouselDialogComponent],
  imports: [
    CommonModule,
    MatDialogModule,
    CarouselWrapperModule
  ],
  exports: [
    MatDialogModule
  ]
})
export class DialogModule { }
