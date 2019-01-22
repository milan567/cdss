import { Component } from '@angular/core';
import {Disease, Patient, Symptom} from '../../models';

import { DiseaseService } from "../../services/disease.service";
import {ActivatedRoute, Router} from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';
import {PatientService} from '../../services/patient.service';

@Component({
  moduleId: module.id,
  selector: 'patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
  providers: [PatientService]

})

export class PatientComponent
{
  public id:string
  public patient:Patient;

  constructor(private patientService: PatientService, private router:  ActivatedRoute) {
    this.router.params.subscribe(
      params => {
        this.id = params['id'];
      }
    );
    this.patientService.getPatient(this.id).subscribe(
      data => {
        this.patient = data;
      });
  }

  ngOnInit() {
    this.router.params.subscribe(
      params => {
        this.id = params['id'];
      }
    )
    this.patientService.getPatient(this.id).subscribe(
      data => {
        this.patient = data;
      });
  }
}



