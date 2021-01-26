import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { ApiService, RequestMethod } from './api.service';
import {of} from 'rxjs';
import { PageableRequest } from 'src/app/core/model/pageable-request';

describe('ApiService', () => {
  let service: ApiService;

  const httpClientStub = {
    request: null
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: HttpClient, useValue: httpClientStub}
      ]
    });
    service = TestBed.inject(ApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependency', () => {
    expect(service.http).toBeTruthy();
  });

  it('should delegate get call', async () => {
    httpClientStub.request = jasmine.createSpy('request').and.returnValue(of([]));
    spyOn(service, 'request').and.callThrough();
    const path = 'something';
    const headers = new HttpHeaders();
    await service.get(path, headers).toPromise();
    expect(service.request).toHaveBeenCalledWith(path, null, RequestMethod.Get, headers);
    expect(httpClientStub.request).toHaveBeenCalled();
  });

  it('should delegate post call', async () => {
    httpClientStub.request = jasmine.createSpy('request').and.returnValue(of({}));
    spyOn(service, 'request').and.callThrough();
    const path = 'something';
    const headers = new HttpHeaders();
    const body = {};
    await service.post(path, body, headers).toPromise();
    expect(service.request).toHaveBeenCalledWith(path, body, RequestMethod.Post, headers);
    expect(httpClientStub.request).toHaveBeenCalled();
  });

  it('should delegate put call', async () => {
    httpClientStub.request = jasmine.createSpy('request').and.returnValue(of({}));
    spyOn(service, 'request').and.callThrough();
    const path = 'something';
    const body = {};
    await service.put(path, body).toPromise();
    expect(service.request).toHaveBeenCalledWith(path, body, RequestMethod.Put);
    expect(httpClientStub.request).toHaveBeenCalled();
  });

  it('should delegate delete call', async () => {
    httpClientStub.request = jasmine.createSpy('request').and.returnValue(of({}));
    spyOn(service, 'request').and.callThrough();
    const path = 'something';
    const body = {};
    await service.delete(path, body).toPromise();
    expect(service.request).toHaveBeenCalledWith(path, body, RequestMethod.Delete);
    expect(httpClientStub.request).toHaveBeenCalled();
  });

  it('should delegate get by page call', async () => {
    httpClientStub.request = jasmine.createSpy('request').and.returnValue(of({}));
    spyOn(service, 'get').and.callThrough();
    spyOn(service, 'request').and.callThrough();
    const path = 'something';
    const pageableRequest: PageableRequest = {
      page: 0,
      size: 10,
      sort: 'id',
      sortOrder: 'desc'
    };
    const fullPath = `${path}?page=${pageableRequest.page}&size=${pageableRequest.size}&sort=${pageableRequest.sort},${pageableRequest.sortOrder}`;
    await service.getByPage(path, pageableRequest).toPromise();
    expect(service.get).toHaveBeenCalledWith(fullPath);
    expect(service.request).toHaveBeenCalledWith(fullPath, null, RequestMethod.Get, undefined);
    expect(httpClientStub.request).toHaveBeenCalled();
  });
});
