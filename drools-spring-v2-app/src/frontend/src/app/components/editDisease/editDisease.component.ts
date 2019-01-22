import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';
import {Disease, Symptom} from "../../models";
import {SymptomService} from "../../services/symptom.service";
import {DiseaseService} from "../../services/disease.service";

@Component({
  moduleId: module.id,
  selector: 'disease',
  templateUrl: './editDisease.component.html',
  styleUrls: ['./editDisease.component.css'],
  providers: [DiseaseService,SymptomService]

})

export class EditDiseaseComponent {

  public creatingSymptom:boolean;
  public text: string = "";
  public symptom: Symptom;
  public symptoms:Symptom[];
  public option:string = "";
  public diseaseSymptoms: Symptom[] = [];
  public name: string ="";
  public disease: Disease;
  public id: string = "";
  postsSubscription:Subscription;

  constructor(private symptomService: SymptomService, private diseaseService:DiseaseService ,private router: Router,
              private activatedRoute:ActivatedRoute) {
    this.activatedRoute.params.subscribe( params => {
      let id = params['id'];
      this.postsSubscription = this.diseaseService.getDisease(id).subscribe(
        data => {
          this.id = id;
          this.disease = data;
          this.name = this.disease.disease;
          this.diseaseSymptoms = this.disease.symptoms
          this.option = this.disease.group.toString();
        });
    } )
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

  public editDisease() {
    if (this.name == "" || this.diseaseSymptoms.length == 0) {
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.disease = new Disease(this.id, this.name, Number(this.option), this.diseaseSymptoms);
      this.diseaseService.editDisease(this.disease).subscribe(
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
