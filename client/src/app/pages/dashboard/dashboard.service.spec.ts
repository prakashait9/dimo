import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { DashboardService } from './dashboard.service';

describe('DashboardService', () => {
  beforeEach(() => TestBed.configureTestingModule({
      imports: [HttpClientModule]
  }));

  it('should be created', () => {
    const service: DashboardService = TestBed.get(DashboardService);
    expect(service).toBeTruthy();
  });
});
