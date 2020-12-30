import { TestBed } from '@angular/core/testing';

import { CulturalOfferingSubtypeService } from './cultural-offering-subtype.service';

describe('CulturalOfferingSubtypeService', () => {
  let service: CulturalOfferingSubtypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CulturalOfferingSubtypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
