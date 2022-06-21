import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './services/auth-guard';

import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { ControlerComponent } from './components/controler/controler.component';
import { LoginComponent } from './components/login/login.component';
import { AdmConfigComponent } from './components/admin/adm-config/adm-config.component';
import { AdmProfessorComponent } from './components/admin/adm-professor/adm-professor.component';
import { AdmSalaComponent } from './components/admin/adm-sala/adm-sala.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {
    path: '',
    canActivate: [AuthGuard],
    children: [
      { path: 'control', component: ControlerComponent}
    ]
  },
  {
    path: 'admin',
    canActivate: [AuthGuard],
    children: [
      { path: 'adms', component: AdmSalaComponent},
      { path: 'admp', component: AdmProfessorComponent},
      { path: 'admc', component: AdmConfigComponent}
    ]
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
