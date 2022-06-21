import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  navigate: any;
  sal="Sala";
  pro="Professor";
  con="Config";
  constructor() { }

  ngOnInit(): void { this.navigate="Sala"; }

  sideGoSala(){ this.navigate="Sala"; }
  sideGoProf(){ this.navigate="Professor"; }
  sideGoConfig(){ this.navigate="Config"; }
}
