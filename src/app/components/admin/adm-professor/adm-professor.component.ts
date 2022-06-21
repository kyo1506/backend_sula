import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

export interface Professor {
  id: number;
  nome: string;
  ativo: string;
  curso: string;
}
const ELEMENT_DATA: Professor[] = [
  { id: 1, nome: 'Ana', ativo: 'Sim', curso: 'ADS' },
  { id: 2, nome: 'Carlos', ativo: 'Sim', curso: 'GE' },
  { id: 3, nome: 'Eduardo', ativo: 'Não', curso: 'ADS' },
  { id: 4, nome: 'Flavia', ativo: 'Não', curso: 'GE' },
  { id: 5, nome: 'Hélio', ativo: 'Não', curso: 'ADS'},
  { id: 6, nome: 'Joelma', ativo: 'Sim', curso: 'SM' },
  { id: 7, nome: 'Karine', ativo: 'Não', curso: 'SM' },
  { id: 8, nome: 'Lucas', ativo: 'Não', curso: 'GE' },
  { id: 9, nome: 'Tulio', ativo: 'Não', curso: 'LOG' },
  { id: 10, nome: 'Wagner', ativo: 'Não', curso: 'LOG' },
];

@Component({
  selector: 'app-adm-professor',
  templateUrl: './adm-professor.component.html',
  styleUrls: ['./adm-professor.component.css']
})
export class AdmProfessorComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nome', 'ativo', 'curso'];
  dataSource = ELEMENT_DATA;
  searchSala: any;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  create(){}
  update(){}
  delet(){}
  sideGoSala(){this.router.navigate(['admin/adms'])}
  sideGoProf(){}
  sideGoConfig(){this.router.navigate(['admin/admc'])}
}
