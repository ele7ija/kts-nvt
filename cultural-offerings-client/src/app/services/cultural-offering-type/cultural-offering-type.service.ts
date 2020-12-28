import { Injectable } from '@angular/core';
import { ApiService } from '../security/api-service/api.service';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { PageableRequest } from 'src/app/model/pageable-request/pageable-request';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferingTypeService {

  private endpoint: string = environment.baseUrl + '/cultural-offerings-types'

  constructor(private apiService: ApiService) {}

  getAll(pageableRequest: PageableRequest): Observable<any>{
    return this.apiService.getByPage(`${this.endpoint}/by-page`, pageableRequest);
  }
}
