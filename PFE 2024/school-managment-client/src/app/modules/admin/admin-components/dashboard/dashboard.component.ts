// src/app/dashboard/dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { ChartData, ChartType } from 'chart.js';
import {DashboardDataService} from "./Dashboard-Data.Service";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {BaseChartDirective} from "ng2-charts";
import {MatList, MatListItem} from "@angular/material/list";
import {NgForOf} from "@angular/common";
import {MatGridList, MatGridTile} from "@angular/material/grid-list";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    MatCardTitle,
    MatCard,
    BaseChartDirective,
    MatCardContent,
    MatList,
    MatListItem,
    NgForOf,
    MatGridTile,
    MatGridList
  ],
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  numberOfVisits: number;
  userStatistics: number;
  formationStatistics: number;
  certificatStatistics: number;
  formationData: any[];
  visitChartData: ChartData<'line'>;
  visitChartType: ChartType = 'line';
  formationChartData: ChartData<'bar'>;
  formationChartType: ChartType = 'bar';

  constructor(private dashboardDataService: DashboardDataService) {}

  ngOnInit(): void {
    this.dashboardDataService.getNumberOfVisits().subscribe(data => this.numberOfVisits = data);
    this.dashboardDataService.getUserStatistics().subscribe(data => this.userStatistics = data);
    this.dashboardDataService.getFormationStatistics().subscribe(data => this.formationStatistics = data);
    this.dashboardDataService.getCertificatStatistics().subscribe(data => this.certificatStatistics = data);
    this.dashboardDataService.getFormationData().subscribe(data => this.formationData = data);

    this.dashboardDataService.getVisitChartData().subscribe(chartData => {
      this.visitChartData = {
        labels: chartData.labels,
        datasets: [
          {
            data: chartData.data,
            label: 'Visites',
            borderColor: '#3e95cd',
            fill: false
          }
        ]
      };
    });

    this.dashboardDataService.getFormationChartData().subscribe(chartData => {
      this.formationChartData = {
        labels: chartData.labels,
        datasets: [
          {
            data: chartData.data,
            label: 'Formations',
            backgroundColor: ['#3e95cd', '#8e5ea2', '#3cba9f'],
          }
        ]
      };
    });
  }
}
