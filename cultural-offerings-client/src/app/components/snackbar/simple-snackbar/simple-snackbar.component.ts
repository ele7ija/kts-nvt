import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { SimpleSnackbarProps } from 'src/app/model/snackbar/simple-snackbar-props/simple-snackbar-props';

@Component({
  selector: 'app-simple-snackbar',
  templateUrl: './simple-snackbar.component.html',
  styleUrls: ['./simple-snackbar.component.scss']
})
export class SimpleSnackbarComponent implements OnInit {

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: SimpleSnackbarProps) { }

  ngOnInit(): void {
  }

}
