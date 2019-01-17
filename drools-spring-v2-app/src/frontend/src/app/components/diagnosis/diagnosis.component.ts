import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';
import {Disease, Symptom} from "../../models";
import {SymptomService} from "../../services/symptom.service";
import {DiagnoseService} from "../../services/diagnose.service";

@Component({
  moduleId: module.id,
  selector: 'diagnosis',
  templateUrl: './diagnosis.component.html',
  styleUrls: ['./diagnosis.component.css'],
  providers: [DiagnoseService,SymptomService]

})

export class DiagnosisComponent{

  public creatingSymptom:boolean;
  public text: string = "";
  public symptom: Symptom;
  public symptoms:Symptom[];
  public option:string = "";
  public diseaseSymptoms: Symptom[] = [];
  public patient: string ="";
  public disease: Disease;

  constructor(private symptomService: SymptomService, private diagnoseService:DiagnoseService ,private router: Router) {
    this.symptomService.getAllSymptoms().subscribe(
      data => {
        this.symptoms = data;
      });
    this.creatingSymptom = false;
  }

  public addSymptom(){
    for (let i = 0 ; i < this.symptoms.length; i++){
      if (this.option == this.symptoms[i].text){
        if (!this.diseaseSymptoms.includes(this.symptoms[i])) {
          this.diseaseSymptoms.push(this.symptoms[i]);
        }
      }


    }
  }


  public removeSymptom(id:number){
    for (let i = 0; i < this.diseaseSymptoms.length; i++){
      if (id.toString() == this.diseaseSymptoms[i].id){
        this.diseaseSymptoms.splice(i,1);
      }
    }
  }


  public createNewSymptom() {
    this.creatingSymptom = !this.creatingSymptom;
  }


  public diagnose() {
    console.log("A");
    if (this.patient == "" || this.diseaseSymptoms.length == 0) {
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.diagnoseService.diagnose(this.patient,this.diseaseSymptoms).subscribe(
        result => {
          console.log(result);
        },
        error => {
          console.log("ERROR");
        }
      );
    }
  }



  public addNewSymptom()
  {
    if (this.text == ""){
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.symptom = new Symptom("",this.text);
      this.symptomService.addSymptom(this.symptom).subscribe(
        data => {
          this.creatingSymptom = !this.creatingSymptom;
          this.symptoms = data.body;
        },
        error => {
          console.log("ERROR");
        }
      )
    }
  }
}
