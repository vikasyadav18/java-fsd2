import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showHome !:boolean;
  showAdmin !:boolean;
  clickLogin(){
    this.showHome=true;
    this.showAdmin=true;
    
  }
  title = 'EventApp';
}
