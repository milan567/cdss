import { Component } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { Subscription} from "rxjs";
import {Disease, Doctor, Symptom} from '../../models';
import {AdminService} from '../../services/admin.service';

@Component({
  moduleId: module.id,
  selector: 'disease',
  templateUrl: './editDoctor.component.html',
  styleUrls: ['./editDoctor.component.css'],
  providers: [AdminService]

})

export class EditDoctorComponent {

  public text: string = "";
  public doctor: Doctor;
  postsSubscription:Subscription;

  constructor(private adminService: AdminService, private activatedRoute:ActivatedRoute) {
    this.activatedRoute.params.subscribe( params => {
      let id = params['id'];
      this.adminService.getDoctor(id).subscribe(
        data => {
          this.doctor = data;
          });
    } );
  }

  saveDoctor(){
    this.adminService.saveDoctor(this.doctor).subscribe(
      data => {
        alert("Doktor uspjesno promjenjen!");
      },
      error => {
        alert("Doslo je do greske!");
        });
  }

}
