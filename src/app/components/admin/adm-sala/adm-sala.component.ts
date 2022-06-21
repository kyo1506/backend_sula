import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { SModalComponent } from './s-modal/s-modal.component';
export interface Sala {
  numero: number;
  tipo: string;
  manutencao: string;
  tamanho: number;
}

const ELEMENT_DATA: Sala[] = [
  { numero: 1, tipo: 'Sala', manutencao: 'Sim', tamanho: 30 },
  { numero: 2, tipo: 'Sala', manutencao: 'Sim', tamanho: 30 },
  { numero: 3, tipo: 'Sala', manutencao: 'Não', tamanho: 35 },
  { numero: 4, tipo: 'Sala', manutencao: 'Não', tamanho: 40 },
  { numero: 5, tipo: 'Laboratório', manutencao: 'Não', tamanho: 30 },
  { numero: 6, tipo: 'Laboratório', manutencao: 'Sim', tamanho: 30 },
  { numero: 7, tipo: 'Laboratório', manutencao: 'Não', tamanho: 40 },
  { numero: 8, tipo: 'Sala', manutencao: 'Não', tamanho: 30 },
  { numero: 9, tipo: 'Sala', manutencao: 'Não', tamanho: 30 },
  { numero: 10, tipo: 'Sala', manutencao: 'Não', tamanho: 30 },
];

@Component({
  selector: 'app-adm-sala',
  templateUrl: './adm-sala.component.html',
  styleUrls: ['./adm-sala.component.css']
})

export class AdmSalaComponent implements OnInit {

  displayedColumns: string[] = ['numero', 'tipo', 'manutencao', 'tamanho'];
  dataSource = ELEMENT_DATA;
  searchSala: any;

  animal: any;
  name: any;


  constructor(private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  create(): void {
    const dialogRef = this.dialog.open(SModalComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });}
  update(){}
  delet(){}
  sideGoSala() { }
  sideGoProf() { this.router.navigate(['admin/admp']) }
  sideGoConfig() { this.router.navigate(['admin/admc']) }
}
