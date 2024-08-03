// src/app/services/dashboard-data.service.ts
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardDataService {
  constructor() {}

  getNumberOfVisits(): Observable<number> {
    return of(10); // Donnée de test
  }

  getUserStatistics(): Observable<number> {
    return of(57); // Donnée de test
  }

  getFormationStatistics(): Observable<number> {
    return of(89); // Donnée de test
  }

  getCertificatStatistics(): Observable<number> {
    return of(45); // Donnée de test
  }

  getFormationData(): Observable<any[]> {
    return of([
      { name: 'Formation A', count: 10 },
      { name: 'Formation B', count: 15 },
      { name: 'Formation C', count: 7 }
    ]); // Données de test
  }

  getVisitChartData(): Observable<any> {
    return of({
      labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
      data: [120, 150, 180, 220, 300, 350]
    });
  }

  getFormationChartData(): Observable<any> {
    return of({
      labels: ['Formation A', 'Formation B', 'Formation C'],
      data: [10, 15, 7]
    });
  }
}
