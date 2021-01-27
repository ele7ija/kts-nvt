import { TestBed } from '@angular/core/testing';
import { RegisterService } from './register.service';
import {of} from 'rxjs';
import { ApiService } from '../api-service/api.service';
import { HttpClient } from '@angular/common/http';
import { RegisterUser } from '../../../model/register-user';

describe('RegisterService', () => {
  let service: RegisterService;

  const apiServiceStub = {
    post: jasmine.createSpy('post').and.returnValue(of({}))
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        RegisterService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: {}}
      ]
    });
    service = TestBed.inject(RegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate post call', async () => {
    const obj = {};
    await service.sendRegistrationRequest(obj as RegisterUser).toPromise();
    expect(apiServiceStub.post).toHaveBeenCalledWith(service.registrationUrl, obj);
  });

});
