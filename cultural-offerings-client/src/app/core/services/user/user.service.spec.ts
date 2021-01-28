import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { ApiService } from '../security/api-service/api.service';
import { UserService } from './user.service';
import { User } from '../../model/user';
import {of} from 'rxjs';

describe('UserService', () => {
  let service: UserService;

  const apiServiceStub = {
    get: jasmine.createSpy('get').and.returnValue(of({})),
    post: jasmine.createSpy('post').and.returnValue(of({})),
    put: jasmine.createSpy('put').and.returnValue(of({})),
    delete: jasmine.createSpy('delete').and.returnValue(of({}))
  };
  const httpClientStub = {};

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        UserService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClientStub}
      ]
    });
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate get all call', async () => {
    await service.getAll({}).toPromise();
    expect(apiServiceStub.get).toHaveBeenCalled();
  });

  it('should delegate get one call', async () => {
    await service.getOne(1).toPromise();
    expect(apiServiceStub.get).toHaveBeenCalledWith(`${service.endpoint}/1`);
  });

  it('should delegate post call', async () => {
    const obj = {};
    await service.insert((obj as User)).toPromise();
    expect(apiServiceStub.post).toHaveBeenCalledWith(service.endpoint, obj);
  });

  it('should delegate put call', async () => {
    const obj = {id: 1};
    await service.update((obj as User)).toPromise();
    expect(apiServiceStub.put).toHaveBeenCalledWith(`${service.endpoint}/${obj.id}`, obj);
  });

  it('should delegate delete call', async () => {
    const obj = 1;
    await service.delete(obj).toPromise();
    expect(apiServiceStub.delete).toHaveBeenCalledWith(`${service.endpoint}/${obj}`);
  });

});
