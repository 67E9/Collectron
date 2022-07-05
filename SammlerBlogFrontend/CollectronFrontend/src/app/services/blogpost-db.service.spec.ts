import { TestBed } from '@angular/core/testing';

import { BlogpostDBService } from './blogpost-db.service';

describe('BlogpostDBService', () => {
  let service: BlogpostDBService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogpostDBService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
