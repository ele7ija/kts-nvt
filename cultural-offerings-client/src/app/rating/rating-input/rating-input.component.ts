import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { RatingInput } from '../../../app/core/model/rating-input';

@Component({
  selector: 'app-rating-input',
  templateUrl: './rating-input.component.html',
  styleUrls: ['./rating-input.component.scss']
})
export class RatingInputComponent implements OnInit {

  @Input()
  selectedValue: number = 0;

  @Input()
  userRatingBeingUploaded: boolean = false;

  @Output()
  ratingAddedEvent: EventEmitter<RatingInput> = new EventEmitter<RatingInput>();

  constructor() {}

  ngOnInit(): void {}

  onRate($event:{oldValue:number, newValue:number}) {
    this.ratingAddedEvent.emit({
      value: $event.newValue,
      date: new Date()
    });
  }

}
