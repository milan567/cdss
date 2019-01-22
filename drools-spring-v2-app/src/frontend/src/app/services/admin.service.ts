import { Injectable } from '@angular/core';
import { Http, HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {Address, Disease, Doctor} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class AdminService
{
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient)
  {
  }

  registerUser(username: string, pass: string, firstname: string, lastname: string, specialist: string)
  {
    let newDoctor = {username: username, password: pass,firstname: firstname, lastname: lastname,specialist: specialist};
    let param = JSON.stringify(newDoctor);
    console.log(param);
    return this.http.post("http://localhost:8080/admin/noviDoktor", param,{headers: this.headers, observe: 'response'});

  }

  getAllDoctors() {
    return this.http.get<Doctor[]>("api/admin/sviDoktori");
  }

  deleteDoctor(doctor:number){
    return this.http.delete<Doctor[]>("api/admin/" + doctor);
  }

  getDoctor(id:string){
    return this.http.get<Doctor>("api/doctor/" + id);
  }

  saveDoctor(doctor:Doctor){
    let param = JSON.stringify(doctor);
    console.log(param);
    return this.http.post<Disease>("api/doktor", param,{headers: this.headers, observe: 'response'});

  }

}


