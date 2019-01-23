import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {Disease, Ingredient, Medication, Symptom} from '../../models';
import {SymptomService} from "../../services/symptom.service";
import {DiseaseService} from "../../services/disease.service";
import {MedicationService} from '../../services/medication.service';
import {IngredientService} from '../../services/ingredient.service';

@Component({
  moduleId: module.id,
  selector: 'medication',
  templateUrl: './addMedication.component.html',
  styleUrls: ['./addMedication.component.css'],
  providers: [MedicationService,IngredientService]

})

export class AddMedicationComponent {

  public creatingIngredient:boolean;
  public text: string = "";
  public ingredient: Ingredient;
  public ingredients: Ingredient[];
  public option:string = "";
  public medicationIngredients: Ingredient[] = [];
  public name: string ="";
  public medication: Medication;
  public ingredientOption: string = "";
  public medicationType: string = "";

  constructor(private medicationService: MedicationService, private ingredientService:IngredientService,
              private router: Router) {
    this.ingredientService.getAllIngredients().subscribe(
      data => {
        this.ingredients = data;
      });
    this.creatingIngredient = false;
  }


  public addIngredient(){
    for (let i = 0 ; i < this.ingredients.length; i++){
      if (this.option == this.ingredients[i].text){
        if (!this.medicationIngredients.includes(this.ingredients[i])) {
          this.medicationIngredients.push(this.ingredients[i]);
        }
      }
    }
  }

  public removeIngredient(id:number){
    for (let i = 0; i < this.medicationIngredients.length; i++){
      if (id.toString() == this.medicationIngredients[i].id){
        this.medicationIngredients.splice(i,1);
      }
    }
  }


  public createNewIngredient() {
    this.creatingIngredient = !this.creatingIngredient;
  }

  public addNewIngredient()
  {
    if (this.text == ""){
      alert("Sva polja moraju biti popunjena");
    }
    else {
      this.ingredient = new Ingredient("",this.text);
      this.ingredientService.addNewIngredient(this.ingredient).subscribe(
        data => {
          this.creatingIngredient = !this.ingredient;
          this.ingredients = data.body;
        },
        error => {
          console.log("ERROR");
        }
      )
    }
  }

  public addNewMedication() {
    if (this.name == "" || this.medicationIngredients.length == 0) {
      alert("Sva polja moraju biti popunjena");
    }
    else {
      if (this.medicationType == "Antibiotik") {
        this.medication = new Medication("", this.name, this.medicationIngredients, "ANTIBIOTIC");
      }
      else if (this.medicationType == "Analgetik"){
        this.medication = new Medication("", this.name, this.medicationIngredients, "ANALGETIC");
      }
      else {
        this.medication = new Medication("", this.name, this.medicationIngredients, "OTHER");
      }
      this.medicationService.addMedication(this.medication).subscribe(
        result => {
          this.router.navigateByUrl("/sviLijekovi");
        },
        error => {
          console.log("ERROR");
        }
      )
    }
  }
}
