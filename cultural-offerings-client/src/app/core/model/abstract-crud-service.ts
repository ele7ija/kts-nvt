import { Observable } from 'rxjs';
import { PageableRequest } from './pageable-request';

export abstract class AbstractCrudService<T> {
  abstract getAll(pageRequest: PageableRequest): Observable<any>;

  abstract insert(entity: T): Observable<T>;

  abstract update(entity: T): Observable<T>;

  abstract delete(id: number): Observable<void>;
}
