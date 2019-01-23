import { Component } from '@angular/core';
import {Disease, Medication} from '../../models';

import { Router } from '@angular/router';
import { Subscription} from "rxjs";
import {MedicationService} from '../../services/medication.service';

@Component({
  moduleId: module.id,
  selector: 'medications',
  templateUrl: './medications.component.html',
  styleUrls: ['./medications.component.css'],
  providers: [MedicationService]

})

export class MedicationsComponent
{

  postsSubscription:Subscription;
  timerSubscription:Subscription;

  medications:Medication[];

  constructor(private medicationService: MedicationService, private router: Router)
  {
    this.refreshData();
  }

  private refreshData(): void {
    this.medicationService.getMedications().subscribe(
      data => {
        this.medications = data.body;
      });

  }

  private deleteMedication(id: number): void {
    this.medicationService.deleteMedication(id).subscribe(
      data => {
            this.medications = data;
          },
      error => {
        console.log("ERROR");
      }
    );
  }


  addMedication(): void {
    this.router.navigate(["dodajLijek"]);
  }


  editMedication(id:number): void {
    this.router.navigate(["izmjenaLijeka/" + id]);
  }

  showMedication(id:number): void {
    this.router.navigate(["/lijek/" + id]);
  }

}



