import { Component } from '@angular/core';
import { Doctor} from "../../models";

import { Router } from '@angular/router';
import {Subscription} from "rxjs";
import {AdminService} from "../../services/admin.service";

@Component({
  moduleId: module.id,
  selector: 'doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css'],
  providers: [AdminService]

})

export class DoctorsComponent
{

  postsSubscription:Subscription;
  timerSubscription:Subscription;
  private doctors: Doctor[];

  constructor(private adminService: AdminService, private router: Router)
  {
    this.refreshData();
  }

  private refreshData(): void {
    this.postsSubscription = this.adminService.getAllDoctors().subscribe(
      data => {
        this.doctors = data;

      });
  }

  editDoctor(id:string){
    this.router.navigateByUrl("/doktor/" + id);
  }

  showDoctor(id:string){
    this.router.navigateByUrl("/doktor/info/" + id);
  }

  private addDoctor(){
    this.router.navigate(['noviDoktor']);
  }


  private deleteDoctor(id: number): void {
    this.adminService.deleteDoctor(id).subscribe(
      result => {
        this.postsSubscription = this.adminService.getAllDoctors().subscribe(
          data => {
            this.doctors = data;

          });
      },
      error => {
        console.log("ERROR");
      }
    );
  }
}



