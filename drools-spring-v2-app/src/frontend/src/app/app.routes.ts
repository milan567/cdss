import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent} from './components/home/home.component';
import {DiagnosisComponent} from './components/diagnosis/diagnosis.component';
import {PatientsComponent} from './components/patients/patients.component';
import {DiseasesComponent} from './components/diseases/diseases.component';
import {DiseaseComponent} from './components/disease/disease.component';
import {EditDiseaseComponent} from './components/editDisease/editDisease.component';
import {PatientsReportComponent} from './components/patientsReport/patientsReport.component';

const appRoutes : Routes =
  [
    {
      path : '',
      component : HomeComponent
    },{
    path : 'dijagnoza/:id',
    component : DiagnosisComponent
  },
    {
      path: 'sviPacijenti',
      component: PatientsComponent,
    },
    {
      path: 'sveBolesti',
      component: DiseasesComponent,
    },
    {
      path: 'bolest/:id',
      component: DiseaseComponent,
    },
    {
      path : 'izmjenaBolesti/:id',
      component : EditDiseaseComponent
    },
    {
      path : 'izvjestaj',
      component : PatientsReportComponent
    }
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);

