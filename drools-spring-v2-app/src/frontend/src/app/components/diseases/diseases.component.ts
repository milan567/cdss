import { Component } from '@angular/core';
import {Disease, Symptom} from "../../models";

import { DiseaseService } from "../../services/disease.service";
import { Router } from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';

@Component({
  moduleId: module.id,
  selector: 'diseases',
  templateUrl: './diseases.component.html',
  styleUrls: ['./diseases.component.css'],
  providers: [DiseaseService]

})

export class DiseasesComponent
{

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  diseases:Disease[];

  constructor(private diseaseService: DiseaseService, private router: Router)
  {
    this.refreshData();
  }

  private refreshData(): void {
    this.postsSubscription = this.diseaseService.getAllDiseases().subscribe(
      data => {
        this.diseases = data;

      });

  }


  addDisease(): void {
    this.router.navigate(["dodajBolest"]);
  }


  editDisease(id:number): void {
    this.router.navigate(["izmjenaBolesti/" + id]);
  }

  showDisease(id:number): void {
    this.router.navigate(["/bolest/" + id]);
  }


  private deleteDisease(id: number): void {
    this.diseaseService.deleteDisease(id).subscribe(
      result => {
        this.postsSubscription = this.diseaseService.getAllDiseases().subscribe(
          data => {
            this.diseases = data;

          });
      },
      error => {
        console.log("ERROR");
      }
    );
  }

}



