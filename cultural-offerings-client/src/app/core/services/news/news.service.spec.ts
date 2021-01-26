import { TestBed } from '@angular/core/testing';
import { NewsService } from './news.service';
import {of} from 'rxjs';
import { ApiService } from '../security/api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { News } from '../../model/news';

describe('NewsService', () => {
  let service: NewsService;

  const apiServiceStub = {
    request: jasmine.createSpy('request').and.returnValue(of({})),
    get: jasmine.createSpy('get').and.returnValue(of({})),
    post: jasmine.createSpy('post').and.returnValue(of({})),
    put: jasmine.createSpy('put').and.returnValue(of({})),
    delete: jasmine.createSpy('delete').and.returnValue(of({}))
  };
  const httpClientStub = {};

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        NewsService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    });
    service = TestBed.inject(NewsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate get one call', async () => {
    await service.getOne('1').toPromise();
    expect(apiServiceStub.get).toHaveBeenCalledWith(`${service.endpoint}/1`);
  });

  it('should delegate post call', async () => {
    const obj = {};
    await service.insert((obj as News)).toPromise();
    expect(apiServiceStub.post).toHaveBeenCalledWith(service.endpoint, obj);
  });

  it('should delegate put call', async () => {
    const obj = {id: 1};
    await service.update((obj as News)).toPromise();
    expect(apiServiceStub.put).toHaveBeenCalledWith(`${service.endpoint}/${obj.id}`, obj);
  });

  it('should delegate delete call', async () => {
    const obj = 1;
    await service.delete(obj).toPromise();
    expect(apiServiceStub.delete).toHaveBeenCalledWith(`${service.endpoint}/${obj}`);
  });

});
