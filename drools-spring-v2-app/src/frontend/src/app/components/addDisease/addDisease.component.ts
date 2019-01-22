import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';
import {Disease, Symptom} from "../../models";
import {SymptomService} from "../../services/symptom.service";
import {DiseaseService} from "../../services/disease.service";

@Component({
  moduleId: module.id,
  selector: 'disease',
  templateUrl: './addDisease.component.html',
  styleUrls: ['./addDisease.component.css'],
  providers: [DiseaseService,SymptomService]

})

export class AddDiseaseComponent {

  public creatingSymptom:boolean;
  public text: string = "";
  public symptom: Symptom;
  public symptoms:Symptom[];
  public option:string = "";
  public diseaseSymptoms: Symptom[] = [];
  public name: string ="";
  public disease: Disease;
  public symptomOption: string = "";

  constructor(private symptomService: SymptomService, private diseaseService:DiseaseService ,private router: Router) {
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


  public addDisease() {
    if (this.name == "" || this.diseaseSymptoms.length == 0) {
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.disease = new Disease("", this.name, 1, this.diseaseSymptoms);
      this.diseaseService.addDisease(this.disease).subscribe(
        result => {
          this.router.navigateByUrl("/sveBolesti");
        },
        error => {
          console.log("ERROR");
        }
      )
    }
  }



  public addNewSymptom()
  {
    if (this.text == ""){
      alert("Sva polja moraju biti popunjena");
    }
    else {
      if (this.symptomOption == "Specificni"){
        this.symptom = new Symptom("",this.text,"SPECIFIC");
      }
      else {
        this.symptom = new Symptom("",this.text,"NONSPECIFIC");
      }
      this.symptomService.addNewSymptom(this.symptom).subscribe(
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
