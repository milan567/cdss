import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Ingredient, Medication} from '../../models';
import {MedicationService} from '../../services/medication.service';
import {IngredientService} from '../../services/ingredient.service';
import {Subscription} from '../../../../node_modules/rxjs';

@Component({
  moduleId: module.id,
  selector: 'medication',
  templateUrl: './editMedication.component.html',
  styleUrls: ['./editMedication.component.css'],
  providers: [MedicationService,IngredientService]

})

export class EditMedicationComponent {

  public creatingIngredient:boolean;
  public text: string = "";
  public ingredient: Ingredient;
  public ingredients: Ingredient[];
  public option:string = "";
  public medicationIngredients: Ingredient[] = [];
  public name: string ="";
  public medication: Medication;
  public medicationType: string = "";
  public id: string = "";
  postsSubscription:Subscription;

  constructor(private medicationService: MedicationService, private ingredientService:IngredientService,
              private router: Router,private activatedRoute:ActivatedRoute) {
    this.activatedRoute.params.subscribe( params => {
      this.id = params['id'];
      this.postsSubscription = this.medicationService.getMedication(this.id).subscribe(
        data => {
          this.medication = data.body;
          console.log(data.body);
          this.name = this.medication.text;
          if (this.medication.medicationType == "ANTIBIOTIC") {
            console.log("Antibiotik");
            this.medicationType = "Antibiotik";
          }
          else if (this.medication.medicationType == "ANALGETIC"){
            console.log("Analgetik");
            this.medicationType = "Analgetik";
          }
          else {
            console.log("Ostalo");
            this.medicationType = "Ostalo";
          }
          this.medicationIngredients = this.medication.ingredients;
          this.medicationType = this.medication.medicationType;
        });
    } )
    this.ingredientService.getAllIngredients().subscribe(
      data => {
        this.ingredients = data;
      });
    this.creatingIngredient = false;
  }


  public addIngredient(){
    for (let i = 0 ; i < this.ingredients.length; i++){
      if (this.option == this.ingredients[i].text){
        let cond = true;
        for (let j = 0; j < this.medicationIngredients.length; j++){
          if (this.option == this.medicationIngredients[j].text){
            cond = false;
          }
        }
        if (cond == true){
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
        this.medication = new Medication(this.id, this.name, this.medicationIngredients, "ANTIBIOTIC");
      }
      else if (this.medicationType == "Analgetik"){
        this.medication = new Medication(this.id, this.name, this.medicationIngredients, "ANALGETIC");
      }
      else {
        this.medication = new Medication(this.id, this.name, this.medicationIngredients, "OTHER");
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
