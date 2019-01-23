import { Injectable } from '@angular/core';
import {Headers, Http, HttpModule} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {Disease, Medication, Symptom} from '../models';

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class MedicationService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }

  getMedications(){
    return this.http.get<Medication[]>("/api/lijekovi/sviLijekovi",{headers: this.headers, observe: 'response'});
  }

  deleteMedication(id: number) {
      return this.http.delete<Medication[]>("/api/lijek/" + id);
  }

  addMedication(medication: Medication) {
    let param = JSON.stringify(medication);
    console.log(param);
    return this.http.post<Medication>("/api/noviLijek", param,{headers: this.headers, observe: 'response'});

  }

  getMedication(id: string) {
    return this.http.get<Medication>("api/lijek/" + id,{headers: this.headers, observe: 'response'});
  }
}
