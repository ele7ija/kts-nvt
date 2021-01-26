import { Location } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { ApiService } from '../api-service/api.service';

import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthService,
        {provide: ApiService, useValue: {}},
        {provide: HttpClient, useValue: {}},
        {provide: Router, useValue: {}},
        {provide: Location, useValue: {}}
      ]
    });
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
    expect(service.router).toBeTruthy();
    expect(service.apiService).toBeTruthy();
  });
});
