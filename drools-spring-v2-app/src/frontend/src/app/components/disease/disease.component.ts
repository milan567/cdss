import { Component } from '@angular/core';
import {Disease, Symptom} from "../../models";

import { DiseaseService } from "../../services/disease.service";
import {ActivatedRoute, Router} from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';

@Component({
  moduleId: module.id,
  selector: 'disease',
  templateUrl: './disease.component.html',
  styleUrls: ['./disease.component.css'],
  providers: [DiseaseService]

})

export class DiseaseComponent
{

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  disease:Disease;
  id : number
  diseaseId : String;

  constructor(private diseaseService: DiseaseService, private router: Router,private activatedRoute: ActivatedRoute)
  {

    this.activatedRoute.params.subscribe( params => {
      let id = params['id'];
      this.postsSubscription = this.diseaseService.getDisease(id).subscribe(
        data => {
          this.disease = data;
          console.log(data)
          this.diseaseId = this.disease.id;
        });
    } )

  }



  private deleteDisease(item: Symptom): void {
    this.diseaseService.deleteSymptomFromDisease(this.diseaseId,item.id).subscribe(
      result => {
        let a = this.disease.symptoms.indexOf(item);
        if (a > 0) {
          this.disease.symptoms.splice(a, 1);
        }
        console.log("RADI");
      },
      error => {
        console.log("ERROR");
      }
    );

  }

}

