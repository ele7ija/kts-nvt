import { Component, Input, OnInit, Output } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import { CulturalOfferingSubtype } from 'src/app/model/cultural-offering-subtype/cultural-offering-subtype';
import { EventEmitter } from '@angular/core';
import { ListChangeEvent } from 'src/app/model/event/list-change-event/list-change-event';
import { ListChangeEventType } from 'src/app/model/event/list-change-event-type/list-change-event-type.enum';

@Component({
  selector: 'app-cultural-offering-subtype-chips',
  templateUrl: './cultural-offering-subtype-chips.component.html',
  styleUrls: ['./cultural-offering-subtype-chips.component.scss']
})
export class CulturalOfferingSubtypeChipsComponent implements OnInit {

  visible: boolean = true;
  selectable: boolean = true;
  removable: boolean = true;
  addOnBlur: boolean = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  @Input()
  typeId: number;

  @Input()
  culturalOfferingSubTypes: CulturalOfferingSubtype[];

  @Output()
  change: EventEmitter<ListChangeEvent<CulturalOfferingSubtype>> = new EventEmitter<ListChangeEvent<CulturalOfferingSubtype>>();

  constructor() { }

  ngOnInit(): void {
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;
    if ((value || '').trim()) {
      this.change.emit({
        item: {
          subTypeName: value,
          typeId: this.typeId
        },
        listChangeEventType: ListChangeEventType.ADD
      });
    }
    if (input) {
      input.value = '';
    }
  }

  remove(item: CulturalOfferingSubtype): void {
    this.change.emit({
      item,
      listChangeEventType: ListChangeEventType.REMOVE
    });
  }
  
  



}
