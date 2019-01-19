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
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
