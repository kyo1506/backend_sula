import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { ControlerComponent } from './components/controler/controler.component';
import { LoginComponent } from './components/login/login.component';
import { SModalComponent } from './components/admin/adm-sala/s-modal/s-modal.component';
import { AdmSalaComponent } from './components/admin/adm-sala/adm-sala.component';
import { AdmProfessorComponent } from './components/admin/adm-professor/adm-professor.component';
import { AdmConfigComponent } from './components/admin/adm-config/adm-config.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule} from '@angular/material/table';
import { UpdateAgendamentoComponent } from './components/controler/update-agendamento/update-agendamento.component';
import { NewAgeModComponent } from './components/controler/new-age-mod/new-age-mod.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminComponent,
    ControlerComponent,
    LoginComponent,
    AdmSalaComponent,
    AdmProfessorComponent,
    AdmConfigComponent,
    SModalComponent,
    UpdateAgendamentoComponent,
    NewAgeModComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatRadioModule,
    MatDividerModule,
    MatListModule,
    MatSelectModule,
    FormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSidenavModule,
    MatTableModule,
    MatDialogModule,
    MatDatepickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
