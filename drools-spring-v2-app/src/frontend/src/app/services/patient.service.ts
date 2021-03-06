import { Injectable } from '@angular/core';
import {Headers, Http, HttpModule} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import { Patient} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class PatientService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }

  getAllPatients(){
    return this.http.get<Patient[]>("/api/sviPacijenti");
  }

  getChronicDiseases(){
    return this.http.get<Patient[]>("/api/pacijentiSaHronicnimOboljenjima",{headers: this.headers, observe: 'response'});
  }

  getPatient(id:string){
    return this.http.get<Patient>("/api/pacijent/" + id);
  }

  getPotentialAddicts() {
    return this.http.get<Patient[]>("/api/potencijalniZavisnici",{headers: this.headers, observe: 'response'});
  }

  getLowImmunityPatients() {
    return this.http.get<Patient[]>("/api/pacijentiSaSlabimImunitetom",{headers: this.headers, observe: 'response'});
  }
}
