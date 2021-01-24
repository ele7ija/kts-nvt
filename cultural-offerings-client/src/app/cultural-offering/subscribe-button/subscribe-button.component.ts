import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Subscription } from 'src/app/core/model/subscription';

@Component({
  selector: 'app-subscribe-button',
  templateUrl: './subscribe-button.component.html',
  styleUrls: ['./subscribe-button.component.scss']
})
export class SubscribeButtonComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input()
  subscription: Subscription;

  @Output()
  subscribedEventEmitter = new EventEmitter<boolean>();

  clicked(): void {
    this.subscribedEventEmitter.emit(true);
  }
}
