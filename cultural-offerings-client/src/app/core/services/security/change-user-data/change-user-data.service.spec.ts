import { TestBed } from '@angular/core/testing';
import { ChangeUserDataService } from './change-user-data.service';
import {of} from 'rxjs';
import { ApiService } from '../api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { UserData } from 'src/app/core/model/current-user';

describe('ChangeUserDataService', () => {
  let service: ChangeUserDataService;

  const apiServiceStub = {
    get: jasmine.createSpy('get').and.returnValue(of({})),
    put: jasmine.createSpy('put').and.returnValue(of({}))
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ChangeUserDataService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: {}}
      ]
    });
    service = TestBed.inject(ChangeUserDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate get call', async () => {
    await service.getDataRequest().toPromise();
    expect(apiServiceStub.get).toHaveBeenCalledWith(service.getUrl);
  });

  it('should delegate put call', async () => {
    const obj = {};
    await service.changeDataRequest(obj as UserData).toPromise();
    expect(apiServiceStub.put).toHaveBeenCalledWith(service.changerUrl, obj);
  });
});
