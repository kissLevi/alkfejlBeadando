import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material';

@Component({
  selector: 'app-ad-extend',
  templateUrl: './ad-extend.component.html',
  styleUrls: ['./ad-extend.component.css']
})
export class AdExtendComponent implements OnInit {
  @Output() extendEvent = new EventEmitter();
  @Output() deleteEvent = new EventEmitter();
  
  private inputDate:string;

  public extend():void{
    this.extendEvent.emit(this.inputDate);
  }

  public remove():void{
    this.deleteEvent.emit(null);
  }

  setDate(event: MatDatepickerInputEvent<Date>) {
    this.inputDate = `${event.value}`;
  }

  constructor() { }

  ngOnInit() {
  }

}
