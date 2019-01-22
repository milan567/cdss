import { Component } from '@angular/core';
import {  ActivatedRoute,Params } from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {NgxPaginationModule} from 'ngx-pagination';
import {Condition, Disease, Medication, Patient, PotentialDisease, Symptom} from '../../models';
import {SymptomService} from "../../services/symptom.service";
import {DiagnoseService} from "../../services/diagnose.service";
import {PatientService} from '../../services/patient.service';
import {DiseaseService} from '../../services/disease.service';
import {MedicationService} from '../../services/medication.service';

@Component({
  moduleId: module.id,
  selector: 'diagnosis',
  templateUrl: './diagnosis.component.html',
  styleUrls: ['./diagnosis.component.css'],
  providers: [DiagnoseService,SymptomService,MedicationService,PatientService,DiseaseService]

})

export class DiagnosisComponent{

  public creatingSymptom:boolean;
  public text: string = "";
  public symptom: Symptom;
  public symptoms:Symptom[];
  public option:string = "";
  public diseaseOption:string = "";
  public medicationOption:string = "";
  public diseaseSymptoms: Symptom[] = [];
  public allDiseases: Disease[] = [];
  public potentialDiseasesList: PotentialDisease[];
  public disease: Disease;
  public finalDisease: Disease;
  public sortedSymptoms: Symptom[] = [];
  public currentPatient: Patient;
  public id:string;
  public potentialDisease: Disease;
  public potentialDiseaseSelected: boolean = false;
  public diagnoseBool: boolean = true;
  public potentialDiseasesSelected: boolean = false;
  public diseaseSelected: boolean = false;
  public finalDiseaseSelected: boolean = false;
  public ownDiagnoseSelected: boolean = false;
  public medications: Medication[] = [];
  public selectedMedications: Medication[] = [];
  public condition: Condition;


  constructor(private symptomService: SymptomService, private diagnoseService:DiagnoseService,
              private patientService: PatientService, private diseaseService:DiseaseService,
              private medicationService: MedicationService,private router:  ActivatedRoute) {}


  ngOnInit() {
    this.router.params.subscribe(
      params => {
        this.id = params['id'];
      }
    )

    this.patientService.getPatient(this.id).subscribe(
      data => {
        this.currentPatient = data;
      }
    );
    this.symptomService.getAllSymptoms().subscribe(
      data => {
        this.symptoms = data;
      });
    this.diseaseService.getAllDiseases().subscribe(
      data => {
        this.allDiseases = data;
      });
    this.medicationService.getMedications().subscribe(
      data => {
        this.medications = data.body;
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
    if (this.currentPatient.id == "" || this.diseaseSymptoms.length == 0) {
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.diagnoseService.diagnose(this.currentPatient.id,this.diseaseSymptoms).subscribe(
        data => {
          console.log(data);
          this.potentialDisease = data.body;
          this.diagnoseBool = false;
          this.potentialDiseaseSelected = true;
        },
        error => {
          alert("Nije pronadjena potencijalna bolest!");
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
      this.symptom = new Symptom("",this.text,"NONSPECIFIC");
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

  public potentialDiseases(){
    if (this.currentPatient.id == "" || this.diseaseSymptoms.length == 0) {
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.diagnoseService.potentialDiseases(this.diseaseSymptoms).subscribe(
        result => {
          this.potentialDiseasesList = result.body;
          if (this.potentialDiseasesList.length == 0){
            alert("Nije pronadjena nijedna potencijalna bolest");
          }
          else{
            this.diagnoseBool = false;
            this.potentialDiseasesSelected = true;
          }
        },
        error => {
          alert("Nije pronadjena nijedna potencijalna bolest");
        }
      );
    }
  }

  public showDiseaseWithSymptoms(){
    this.diagnoseBool = false;
    this.diseaseSelected = true;
  }

  public back(){
    this.potentialDiseasesSelected = false;
    this.potentialDiseaseSelected = false;
    this.diseaseSelected = false;
    this.diagnoseBool = true;
  }

  getConfirmedDisease(){
    for (let i = 0 ; i < this.allDiseases.length; i++) {
      if (this.diseaseOption == this.allDiseases[i].disease) {
        this.confirmDisease(this.allDiseases[i].id);
      }
    }
  }

  public getDiseaseWithSymptoms(){
    this.sortedSymptoms = [];
    for (let i = 0 ; i < this.allDiseases.length; i++){
      if (this.diseaseOption == this.allDiseases[i].disease){
        this.diseaseService.getDiseaseWithSortedSymptoms(this.allDiseases[i].id).subscribe(
          data => {
            this.sortedSymptoms = data.body;
            this.diagnoseBool = false;
            this.diseaseSelected = true;
          },
          error => {
            alert("Nije pronadjena potencijalna bolest!");
          }
        );
      }
    }
  }

  confirmDisease(id:string){
    for (let i = 0 ; i < this.allDiseases.length; i++){
      if (id == this.allDiseases[i].id){
        this.finalDisease = this.allDiseases[i];
      }
    }
    this.potentialDiseaseSelected = false;
    this.diagnoseBool = false;
    this.potentialDiseasesSelected = false;
    this.diseaseSelected = false;
    this.ownDiagnoseSelected = false;
    this.finalDiseaseSelected = true;
  }

  public addMedication(){
    for (let i = 0 ; i < this.medications.length; i++){
      if (this.medicationOption == this.medications[i].text){
        if (!this.selectedMedications.includes(this.medications[i])) {
          this.diagnoseService.checkPatientAllergies(this.currentPatient.id,this.medications[i].id).subscribe(
        data => {
              this.condition = data.body;
              if(data.body.conditionSatisfied == true){
                this.selectedMedications.push(this.medications[i]);
              }
              else{
                for(let j = 0; j < this.condition.message.length; j++){
                  alert(this.condition.message[j]);
                }
              }
              },
            error => {
              console.log(error);
            });
          }
        }
      }
  }

  public removeMedication(id:number){
    for (let i = 0; i < this.selectedMedications.length; i++){
      if (id.toString() == this.selectedMedications[i].id){
        this.selectedMedications.splice(i,1);
      }
    }
  }

  finishDiagnose(){
    this.diagnoseService.finishDiagnose(this.currentPatient.id,this.diseaseSymptoms,
      this.finalDisease,this.selectedMedications).subscribe(
      data => {
        alert("Pregled uspjesno zavrsen!")
      },
      error => {
        alert("Doslo je do greske!");
      });
  }

  getOwnDiagnose(){
    this.diagnoseBool = false;
    this.ownDiagnoseSelected = true;
  }

}
