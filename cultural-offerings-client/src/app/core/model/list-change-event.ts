import { ListChangeEventType } from './list-change-event-type.enum';

export interface ListChangeEvent<T> {
  item?: T;
  listChangeEventType?: ListChangeEventType;
}
