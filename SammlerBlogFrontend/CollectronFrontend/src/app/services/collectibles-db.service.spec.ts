import { TestBed } from '@angular/core/testing';

import { CollectiblesDBService } from './collectibles-db.service';

describe('CollectiblesDBService', () => {
  let service: CollectiblesDBService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CollectiblesDBService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
