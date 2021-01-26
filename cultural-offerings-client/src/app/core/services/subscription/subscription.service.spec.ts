import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { ApiService } from '../security/api-service/api.service';
import { SubscriptionService } from './subscription.service';
import {of} from 'rxjs';
import { Subscription } from '../../model/subscription';

describe('SubscriptionService', () => {
  let service: SubscriptionService;

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
        SubscriptionService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    });
    service = TestBed.inject(SubscriptionService);
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
    await service.insert((obj as Subscription)).toPromise();
    expect(apiServiceStub.post).toHaveBeenCalledWith(service.endpoint, obj);
  });

  it('should delegate put call', async () => {
    const obj = {id: 1};
    await service.update((obj as Subscription)).toPromise();
    expect(apiServiceStub.put).toHaveBeenCalledWith(`${service.endpoint}/${obj.id}`, obj);
  });

  it('should delegate delete call', async () => {
    const obj = 1;
    await service.delete(obj).toPromise();
    expect(apiServiceStub.delete).toHaveBeenCalledWith(`${service.endpoint}/${obj}`);
  });
  
});
