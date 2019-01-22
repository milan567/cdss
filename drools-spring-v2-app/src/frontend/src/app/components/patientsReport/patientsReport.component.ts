import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {Patient} from "../../models";
import {PatientService} from '../../services/patient.service';

@Component({
  moduleId: module.id,
  selector: 'patients',
  templateUrl: './patientsReport.component.html',
  styleUrls: ['./patientsReport.component.css'],
  providers: []

})

export class PatientsReportComponent{

  public patients:Patient[];
  public initPage: boolean = true;

  constructor(private patientService: PatientService,private router: Router) {
    this.patientService.getAllPatients().subscribe(
      data => {
        this.initPage = true;
        this.patients = data;
      });
  }

  getChronicDiseases(){
    this.patientService.getChronicDiseases().subscribe(
      data => {
        this.initPage = false;
        this.patients = data.body;
      });
  }

  getPotentialAddicts(){
    this.patientService.getPotentialAddicts().subscribe(
      data => {
        this.initPage = false;
        this.patients = data.body;
      });
  }

  getLowImmunityPatients(){
    this.patientService.getLowImmunityPatients().subscribe(
      data => {
        this.initPage = false;
        this.patients = data.body;
      });
  }

  getDiagnose(id:string){
    this.router.navigateByUrl("/dijagnoza/"+id);
  };

}
