import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent} from './components/home/home.component';
import {DiagnosisComponent} from './components/diagnosis/diagnosis.component';
import {PatientsComponent} from './components/patients/patients.component';
import {DiseasesComponent} from './components/diseases/diseases.component';
import {DiseaseComponent} from './components/disease/disease.component';
import {EditDiseaseComponent} from './components/editDisease/editDisease.component';
import {PatientsReportComponent} from './components/patientsReport/patientsReport.component';
import {PatientComponent} from './components/patient/patient.component';
import {AddDiseaseComponent} from './components/addDisease/addDisease.component';
import {DoctorsComponent} from './components/doctors/doctors.component';
import {EditDoctorComponent} from './components/editDoctor/editDoctor.component';
import {DoctorComponent} from './components/doctor/doctor.component';
import {MedicationsComponent} from './components/medications/medications.component';
import {AddMedicationComponent} from './components/addMedication/addMedication.component';
import {EditMedicationComponent} from './components/editMedication/editMedication.component';
import {MedicationComponent} from './components/medication/medication.component';

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
    },
    {
      path : 'pacijent/:id',
      component : PatientComponent
    },
    {
      path: 'dodajBolest',
      component: AddDiseaseComponent
    },
    {
      path: 'sviDoktori',
      component: DoctorsComponent
    },
    {
      path: 'doktor/:id',
      component: EditDoctorComponent
    },
    {
      path: 'doktor/info/:id',
      component: DoctorComponent
    },
    {
       path: 'sviLijekovi',
       component: MedicationsComponent
    },
    {
      path: 'dodajLijek',
      component: AddMedicationComponent
    },
    {
      path : 'izmjenaLijeka/:id',
      component : EditMedicationComponent
    },
    {
      path: 'lijek/:id',
      component: MedicationComponent,
    },
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);

