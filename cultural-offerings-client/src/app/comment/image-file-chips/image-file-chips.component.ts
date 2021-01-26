import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ListChangeEvent } from 'src/app/core/model/list-change-event';
import { ListChangeEventType } from 'src/app/core/model/list-change-event-type.enum';

@Component({
  selector: 'app-image-file-chips',
  templateUrl: './image-file-chips.component.html',
  styleUrls: ['./image-file-chips.component.scss']
})
export class ImageFileChipsComponent implements OnInit {

  visible = true;
  selectable = true;
  removable = true;

  @Input()
  chosenFiles: File[];

  @Input()
  chipsHeight: number;

  @Output()
  changeEventEmitter: EventEmitter<ListChangeEvent<File>> = new EventEmitter<ListChangeEvent<File>>();

  constructor() { }

  ngOnInit(): void {
  }

  remove(item: File): void {
    this.changeEventEmitter.emit({
      item,
      listChangeEventType: ListChangeEventType.REMOVE
    });
  }

}
