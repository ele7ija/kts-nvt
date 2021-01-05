import { TestBed } from '@angular/core/testing';

import { CulturalOfferingTypeService } from './cultural-offering-type.service';

describe('CulturalOfferingTypeService', () => {
  let service: CulturalOfferingTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CulturalOfferingTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
