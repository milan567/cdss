import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent} from './components/home/home.component';
import {DiagnosisComponent} from './components/diagnosis/diagnosis.component';

const appRoutes : Routes =
  [
    {
      path : '',
      component : HomeComponent
    },{
    path : 'dijagnoza',
    component : DiagnosisComponent
  },

  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);

