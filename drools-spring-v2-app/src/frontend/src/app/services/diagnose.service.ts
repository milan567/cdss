import { Injectable } from '@angular/core';
import {Headers, Http, HttpModule} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {Condition, Disease, Medication, PotentialDisease, Symptom} from '../models';

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class DiagnoseService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }

  diagnose(id:string, symptoms:Symptom[]){
    let param =  {id: id, symptoms: symptoms}
    console.log(param);
    return this.http.post<Disease>("/api/doktor/dijagnozaPacijenta", param,{headers: this.headers, observe: 'response'});

  }


  potentialDiseases(symptoms:Symptom[]){
    return this.http.post<PotentialDisease[]>("/api/doktor/potencijalneBolesti", symptoms,{headers: this.headers, observe: 'response'});
  }

  checkPatientAllergies(id: string, medication: string) {
    return this.http.get<Condition>("/api/pacijent/"+id+"/lijek/"+medication,{headers: this.headers, observe: 'response'});
  }

  finishDiagnose(patientId:string,symptoms:Symptom[],disease:Disease,medications:Medication[]){
    let param = {patientId:patientId, symptoms:symptoms,
      disease:disease, medications:medications};
    return this.http.post<PotentialDisease[]>("api/pregled/noviPregled", param,{headers: this.headers, observe: 'response'});
  }
}
