import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { ApiService } from '../api-service/api.service';
import { ChangePasswordService } from './change-password.service';
import {of} from 'rxjs';
import { ChangePassword } from 'src/app/core/model/change-password';

describe('ChangePasswordService', () => {
  let service: ChangePasswordService;

  const apiServiceStub = {
    put: jasmine.createSpy('put').and.returnValue(of({})),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ChangePasswordService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: {}}
      ]
    });
    service = TestBed.inject(ChangePasswordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate put call', async () => {
    const obj = {};
    await service.sendChangePassRequest(obj as ChangePassword).toPromise();
    expect(apiServiceStub.put).toHaveBeenCalledWith(service.changerUrl, obj);
  });

});
