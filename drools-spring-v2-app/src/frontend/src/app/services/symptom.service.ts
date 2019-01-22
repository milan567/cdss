import { Injectable } from '@angular/core';
import { Http, HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {Disease, Symptom} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class SymptomService{
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }

  getAllSymptoms()
  {
    return this.http.get<Symptom[]>("http://localhost:8080/simptomi/sviSimptomi");
  }

  addSymptom(symptom:Symptom){
    let param = JSON.stringify(symptom);
    console.log(param);
    return this.http.post<Symptom[]>("/api/simptom/noviSimptom", param,{headers: this.headers, observe: 'response'});

  }


  addNewSymptom(symptom:Symptom){
    let param = JSON.stringify(symptom);
    console.log(param);
    return this.http.post<Symptom[]>("/api/simptom/dodajNoviSimptom", param,{headers: this.headers, observe: 'response'});

  }
}
