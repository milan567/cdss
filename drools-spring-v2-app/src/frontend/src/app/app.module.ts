import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import {FormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './auth/token.interceptor';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {enableProdMode} from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppComponent }  from './app.component';
import {HomeComponent} from "./components/home/home.component";
import { PatientsComponent} from './components/patients/patients.component';
import {DiagnosisComponent} from './components/diagnosis/diagnosis.component';

import { routing } from "./app.routes";
import {DiagnoseService} from './services/diagnose.service';
import {SymptomService} from './services/symptom.service';
import { AuthenticationService } from "./services/authentication.service";
import {PatientService} from './services/patient.service';
import {DiseasesComponent} from './components/diseases/diseases.component';
import {DiseaseComponent} from './components/disease/disease.component';
import {EditDiseaseComponent} from './components/editDisease/editDisease.component';
import {MedicationService} from './services/medication.service';
import {DiseaseService} from './services/disease.service';
import {PatientsReportComponent} from './components/patientsReport/patientsReport.component';
import {PatientComponent} from './components/patient/patient.component';
import {AddDiseaseComponent} from './components/addDisease/addDisease.component';
import {DoctorsComponent} from './components/doctors/doctors.component';
import {EditDoctorComponent} from './components/editDoctor/editDoctor.component';
import {DoctorComponent} from './components/doctor/doctor.component';

enableProdMode();


@NgModule({
  imports:      [
    BrowserModule,
    routing,
    HttpModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  declarations: [
    AppComponent,
    NavbarComponent,

    HomeComponent,
    DiagnosisComponent,
    PatientsComponent,
    DiseasesComponent,
    DiseaseComponent,
    EditDiseaseComponent,
    PatientsReportComponent,
    PatientComponent,
    AddDiseaseComponent,
    DoctorsComponent,
    EditDoctorComponent,
    DoctorComponent,
  ],
  providers: [
    /*{

      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },*/
    AuthenticationService,
    SymptomService,
    DiagnoseService,
    PatientService,
    DiseaseService,
    MedicationService,
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
