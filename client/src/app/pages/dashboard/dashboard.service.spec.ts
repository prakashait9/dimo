import { TestBed } from '@angular/core/testing';

import { DashboardService } from './dashboard.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('DashboardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DashboardService = TestBed.get(DashboardService);
    expect(service).toBeTruthy();
  });
});
