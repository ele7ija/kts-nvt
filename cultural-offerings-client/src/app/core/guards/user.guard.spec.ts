import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AuthService } from '../services/security/auth-service/auth.service';

import { UserGuard } from './user.guard';

describe('UserGuard', () => {
  let guard: UserGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: Router, useValue: {}},
        {provide: AuthService, useValue: {}}
      ]
    });
    guard = TestBed.inject(UserGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(guard.router).toBeTruthy();
    expect(guard.authService).toBeTruthy();
  });
});
