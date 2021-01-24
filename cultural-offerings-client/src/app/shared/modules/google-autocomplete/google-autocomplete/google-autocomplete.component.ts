import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Location } from '../../../../../app/core/model/location';

@Component({
  selector: 'app-google-autocomplete',
  templateUrl: './google-autocomplete.component.html',
  styleUrls: ['./google-autocomplete.component.scss']
})
export class GoogleAutocompleteComponent implements OnInit {

  @Input()
  location: Location;

  @Output()
  locationChangedEvent: EventEmitter<Location> = new EventEmitter<Location>()

  constructor(){}

  ngOnInit(): void {}

  handleAddressChange(event){
    const newLocation: Location = {
      name: event.name,
      latitude: event.geometry && event.geometry.location ? event.geometry.location.lat() : null,
      longitude: event.geometry && event.geometry.location ? event.geometry.location.lng() : null
    };
    this.locationChangedEvent.emit(newLocation);
  }

  handleAddressClear(){
    this.locationChangedEvent.emit(null);
  }
}
