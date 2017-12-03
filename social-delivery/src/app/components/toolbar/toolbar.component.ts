import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  @Input()
  public loggedIn:boolean;

  @Output() hideEvent= new EventEmitter();
  
  public logout(): void{
    this.hideEvent.emit(null);
  }
  
  constructor() {

  }

  ngOnInit() {
  }

}
