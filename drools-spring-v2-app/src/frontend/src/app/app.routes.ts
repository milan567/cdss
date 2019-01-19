import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent} from './components/home/home.component';
import {DiagnosisComponent} from './components/diagnosis/diagnosis.component';
import {PatientsComponent} from './components/patients/patients.component';

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
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);

