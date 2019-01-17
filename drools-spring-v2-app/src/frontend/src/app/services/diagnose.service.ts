import { Injectable } from '@angular/core';
import {Headers, Http, HttpModule} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import { Disease, Symptom } from "../models";

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
}
