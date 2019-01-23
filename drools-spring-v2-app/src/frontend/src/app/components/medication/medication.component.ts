import { Component } from '@angular/core';
import {Disease, Medication, Symptom} from '../../models';

import { DiseaseService } from "../../services/disease.service";
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from "rxjs";
import {MedicationService} from '../../services/medication.service';

@Component({
  moduleId: module.id,
  selector: 'medication',
  templateUrl: './medication.component.html',
  styleUrls: ['./medication.component.css'],
  providers: [MedicationService]

})

export class MedicationComponent
{

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  private medication:Medication;
  private id : string;
  private medicationId : String;

  constructor(private medicationService: MedicationService, private router: Router,private activatedRoute: ActivatedRoute)
  {

    this.activatedRoute.params.subscribe( params => {
      this.id = params['id'];
      this.postsSubscription = this.medicationService.getMedication(this.id).subscribe(
        data => {
          this.medication = data.body;
          this.medicationId = this.medication.id;
        });
    } )

  }
}

