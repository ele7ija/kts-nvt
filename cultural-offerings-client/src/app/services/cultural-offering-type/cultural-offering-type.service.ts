import { Injectable } from '@angular/core';
import { ApiService } from '../security/api-service/api.service';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CulturalOfferingType } from 'src/app/model/cultural-offering-type/cultural-offering-type';

@Injectable({
  providedIn: 'root'
})
export class CulturalOfferingTypeService {

  private endpoint: string = environment.baseUrl + '/auth/cultural-offerings-types'

  constructor(private apiService: ApiService) {}

  getAll(): Observable<CulturalOfferingType[]>{
    return this.apiService.get(this.endpoint);
  }
}
