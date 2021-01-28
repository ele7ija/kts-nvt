import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { AuthService } from '../../../app/core/services/security/auth-service/auth.service';
import { RatingInput } from '../../../app/core/model/rating-input';

@Component({
  selector: 'app-rating-input',
  templateUrl: './rating-input.component.html',
  styleUrls: ['./rating-input.component.scss']
})
export class RatingInputComponent implements OnInit {

  @Input()
  selectedValue = 0;

  @Input()
  userRatingBeingUploaded = false;

  @Output()
  ratingAddedEvent: EventEmitter<RatingInput> = new EventEmitter<RatingInput>();

  constructor(
    public authService: AuthService
  ) { }

  ngOnInit(): void { }

  onRate($event: { oldValue: number, newValue: number }): void {
    this.ratingAddedEvent.emit({
      value: $event.newValue,
      date: new Date()
    });
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

}
