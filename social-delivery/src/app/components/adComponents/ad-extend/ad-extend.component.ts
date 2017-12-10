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
  

  private timeInMilliseconds:number;

  private timeInHoursMinutes:string;

  public extend():void{
    this.extendEvent.emit(this.timeInMilliseconds);
  }

  public remove():void{
    this.deleteEvent.emit(null);
  }

  public valueChanged(change){

    this.timeInMilliseconds = (change.value as number)*3600000;
    const timeLeft: Date = new Date(this.timeInMilliseconds);
    // const h:string = (timeLeft.getHours()-1)+"";
    // const m:string = timeLeft.getMinutes().toString();
    // this.timeInHoursMinutes = h+"óra"+m+"perc"
  }


  constructor() { }

  ngOnInit() {
  }

}
