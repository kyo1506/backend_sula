import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UpdateAgendamentoComponent } from './update-agendamento/update-agendamento.component';
import { NewAgeModComponent } from './new-age-mod/new-age-mod.component';

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
  selector: 'app-controler',
  templateUrl: './controler.component.html',
  styleUrls: ['./controler.component.css']
})
export class ControlerComponent implements OnInit {

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
  horasegs: Hora[] = [
    {value:'07:00: Lab_5-MicroSistemas'},{value:'07:30: Lab_5-MicroSistemas'},
    {value:'08:00: Lab_5-MicroSistemas'},{value:'08:30: Lab_5-MicroSistemas'}   
  ]
  horaquas: Hora[] = [
    {value:'09:00: Sala_4-HTML e CSS'},{value:'09:30 Sala_4-HTML e CSS'},
    {value:'10:00 Sala_4-HTML e CSS'},{value:'10:30 Sala_4-HTML e CSS'},
    {value:'11:00 Sala_4-HTML e CSS'},{value:'11:30Sala_4-HTML e CSS'}   
  ]
  horasexs: Hora[] = [
    {value:'09:00: Sala_6-IA'},{value:'09:30 Sala_6-IA'},
    {value:'10:00 Sala_6-IA'},{value:'10:30 Sala_6-IA'},
    {value:'11:00 Sala_6-IA'},{value:'11:30 Sala_6-IA'}    
  ]


  dates: string[] = ['Semanal', 'Mensal'];
  constructor(private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.tipoData = 'Semanal';
  }

  goLogin(){
    this.router.navigate(['login']);
  }
  create(){}
  update(){}
  delet(){}
  openModal(){
    const dialogRef = this.dialog.open(UpdateAgendamentoComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  newAgeMod(){
    const dialogRef = this.dialog.open(NewAgeModComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
