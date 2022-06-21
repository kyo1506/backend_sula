import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

interface Curso {
  value: string;
  viewValue: string;
}
interface Semestre {
  value: string;
  viewValue: string;
}
interface Periodo {
  value: string;
  viewValue: string;
}
interface Hora {
  value: string;
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  tipoData:any;
  selectedCurso: any;
  sem = 'Semanal';
  men = 'Mensal';
  cursos: Curso[] = [
    {value:'ads', viewValue:'Análise e Desenvolvimento de Sistemas'},
    {value:'dsm', viewValue:'Desenvolvimento de Software Multiplataforma'},
    {value:'gem', viewValue:'Gestão Empresarial'},
    {value:'log', viewValue:'Logística'}
  ];
  selectedSemestre: any;
  semestres: Semestre[] = [
    {value:'1', viewValue:'1'},
    {value:'2', viewValue:'2'},
    {value:'3', viewValue:'3'},
    {value:'4', viewValue:'4'},
    {value:'5', viewValue:'5'},
    {value:'6', viewValue:'6'},
  ];
  selectedPeriodo: any;
  periodos: Periodo[] = [
    {value:'mat', viewValue:'Matutino'},
    {value:'ves', viewValue:'Vespertino'},
    {value:'not', viewValue:'Noturno'}
  ];
  horas: Hora[] = [
    {value:'07:00: Lab_5-MicroSistemas'},{value:'07:30: Lab_5-MicroSistemas'},{value:'08:00: Lab_5-MicroSistemas'},{value:'08:30: Lab_5-MicroSistemas'},{value:'09:00: Sala_1-Gestão_Empresarial'},{value:'09:30 Sala_1-Gestão_Empresarial'},{value:'10:00 Sala_1-Gestão_Empresarial'},{value:'10:30 Sala_1-Gestão_Empresarial'},{value:'11:00 Sala_1-Gestão_Empresarial'},{value:'11:30 Sala_1-Gestão_Empresarial'},    
  ]


  dates: string[] = ['Semanal', 'Mensal'];

  constructor(private router: Router) {};

  ngOnInit(): void {};

  goLogin(){
    this.router.navigate(['login']);
  }

}
