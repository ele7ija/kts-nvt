import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { RatingInput } from '../../../app/core/model/rating-input';

@Component({
  selector: 'app-rating-input',
  templateUrl: './rating-input.component.html',
  styleUrls: ['./rating-input.component.scss']
})
export class RatingInputComponent implements OnInit {

  selectedValue: number = 0;

  @Output()
  ratingAddedEvent: EventEmitter<RatingInput> = new EventEmitter<RatingInput>();

  constructor() {}

  ngOnInit(): void {}

  onRate($event:{oldValue:number, newValue:number}) {
    this.selectedValue = $event.newValue
  }
  

  add(){
    this.ratingAddedEvent.emit({
      value: this.selectedValue,
      date: new Date()
    });
  }

}
