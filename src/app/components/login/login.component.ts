import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  color ="red";
  userName="";
  dialog: any;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  goBack(){
    this.router.navigate(['']);
  }
  goLog(){
    if(this.userName==="Admin"){
      this.router.navigate(['/admin']);
    }
    if(this.userName==="Professor"){
      this.router.navigate(['/control'])
    }
    else{

    }
  }
}
