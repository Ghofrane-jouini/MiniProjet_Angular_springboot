import { TestBed } from '@angular/core/testing';
import { Chanson } from '../model/chanson.model';

describe('Chanson', () => {
  let service: Chanson;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Chanson);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});