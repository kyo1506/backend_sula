import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adm-config',
  templateUrl: './adm-config.component.html',
  styleUrls: ['./adm-config.component.css']
})
export class AdmConfigComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  sideGoSala(){this.router.navigate(['admin/adms'])}
  sideGoProf(){this.router.navigate(['admin/admp'])}
  sideGoConfig(){}
}
