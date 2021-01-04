import { ListChangeEventType } from "../list-change-event-type/list-change-event-type.enum";

export class ListChangeEvent<T> {
  item?: T;
  listChangeEventType?: ListChangeEventType;
}
