import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {Patient} from "../../models";
import {PatientService} from '../../services/patient.service';

@Component({
  moduleId: module.id,
  selector: 'patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css'],
  providers: []

})

export class PatientsComponent{

  public patients:Patient[];

  constructor(private patientService: PatientService,private router: Router) {
    this.patientService.getAllPatients().subscribe(
      data => {
        this.patients = data;
      });
  }

  getDiagnose(id:string){
    this.router.navigateByUrl("/dijagnoza/"+id);
  };

}
