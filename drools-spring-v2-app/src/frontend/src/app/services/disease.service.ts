import { Injectable } from '@angular/core';
import {Headers, Http, HttpModule} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import { Disease, Symptom } from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class DiseaseService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }

  getAllDiseases()
  {
    return this.http.get<Disease[]>("http://localhost:8080/bolesti/sveBolesti");
  }

  getDisease(id:number)
  {
    return this.http.get<Disease>("http://localhost:8080/bolest/" + id);
  }

  getDiseaseWithSortedSymptoms(id:string)
  {
    return this.http.get<Symptom[]>("/api/bolestSaSimptomima/" + id,{headers: this.headers, observe: 'response'});
  }

  addDisease(disease:Disease){
    let param = JSON.stringify(disease);
    console.log(param);
    return this.http.post<Disease>("http://localhost:8080/bolest/novaBolest", param,{headers: this.headers, observe: 'response'});

  }

  deleteSymptomFromDisease(disease:String,symptom:String){
    return this.http.delete<void>("http://localhost:8080/bolest/" + disease + "/simptom/" + symptom);
  }

  deleteDisease(disease:number){
    return this.http.delete<Disease[]>("http://localhost:8080/bolest/" + disease);
  }

  editDisease(disease:Disease){
    let param = JSON.stringify(disease);
    console.log(param);
    return this.http.post<Disease>("http://localhost:8080/bolest/izmjeniBolest", param,{headers: this.headers, observe: 'response'});

  }
}
