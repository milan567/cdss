import { Injectable } from '@angular/core';
import {Headers, Http, HttpModule} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {Ingredient, Symptom} from '../models';

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class IngredientService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }

  getAllIngredients() {
    return this.http.get<Ingredient[]>("api/sastojci/sviSastojci");
  }

  addNewIngredient(ingredient:Ingredient){
    let param = JSON.stringify(ingredient);
    return this.http.post<Symptom[]>("/api/sastojak/dodajNoviSastojak", param,{headers: this.headers, observe: 'response'});
  }
}
