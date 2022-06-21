import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-agendamento',
  templateUrl: './update-agendamento.component.html',
  styleUrls: ['./update-agendamento.component.css']
})
export class UpdateAgendamentoComponent implements OnInit {

  update: any;
  ups: string[] = ['Atualização', 'Cancelamento'];
  atu = 'Atualização';
  can = 'Cancelamento';
  constructor() { }

  ngOnInit(): void {
  }

}
