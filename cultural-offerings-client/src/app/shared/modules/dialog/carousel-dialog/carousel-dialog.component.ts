import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CarouselProps } from 'src/app/core/model/carousel-props';

@Component({
  selector: 'app-carousel-dialog',
  templateUrl: './carousel-dialog.component.html',
  styleUrls: ['./carousel-dialog.component.scss']
})
export class CarouselDialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: CarouselProps
  ) { }

  ngOnInit(): void {
  }

}
