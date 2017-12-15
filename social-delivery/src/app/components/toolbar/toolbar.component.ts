import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import { User } from '../../classes/user';


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  @Input()
  public loggedIn:boolean;
  @Input()
  public user:User;

  @Output() hideEvent= new EventEmitter();
  

  public logout(): void{
    this.hideEvent.emit(null);
  }
  
  constructor() {

  }

  ngOnInit() {
  }

}
