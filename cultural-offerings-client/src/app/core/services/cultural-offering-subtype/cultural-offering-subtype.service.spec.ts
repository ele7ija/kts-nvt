import { TestBed } from '@angular/core/testing';
import { CulturalOfferingSubtypeService } from './cultural-offering-subtype.service';
import {of} from 'rxjs';
import { ApiService } from '../security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { CulturalOfferingSubtype } from '../../model/cultural-offering-subtype';

describe('CulturalOfferingSubtypeService', () => {
  let service: CulturalOfferingSubtypeService;

  const apiServiceStub = {
    get: jasmine.createSpy('get').and.returnValue(of({})),
    getByPage: jasmine.createSpy('getByPage').and.returnValue(of({})),
    post: jasmine.createSpy('post').and.returnValue(of({})),
    put: jasmine.createSpy('put').and.returnValue(of({})),
    delete: jasmine.createSpy('delete').and.returnValue(of({}))
  };
  const httpClientStub = {};

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        CulturalOfferingSubtypeService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    });
    service = TestBed.inject(CulturalOfferingSubtypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate get all by page call', async () => {
    const obj = {};
    await service.getAll(obj).toPromise();
    expect(apiServiceStub.getByPage).toHaveBeenCalledWith(`${service.endpoint}/by-page`, obj);
  });

  it('should delegate get all normal call', async () => {
    await service.getAllEntities().toPromise();
    expect(apiServiceStub.get).toHaveBeenCalledWith(service.endpoint);
  });

  it('should delegate post call', async () => {
    const obj = {};
    await service.insert((obj as CulturalOfferingSubtype)).toPromise();
    expect(apiServiceStub.post).toHaveBeenCalledWith(service.endpoint, obj);
  });

  it('should delegate put call', async () => {
    const obj = {id: 1};
    await service.update((obj as CulturalOfferingSubtype)).toPromise();
    expect(apiServiceStub.put).toHaveBeenCalledWith(`${service.endpoint}/${obj.id}`, obj);
  });

  it('should delegate delete call', async () => {
    const obj = 1;
    await service.delete(obj).toPromise();
    expect(apiServiceStub.delete).toHaveBeenCalledWith(`${service.endpoint}/${obj}`);
  });

});
