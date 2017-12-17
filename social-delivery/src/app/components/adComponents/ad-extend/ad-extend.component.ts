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

  private error:string = "";

  public extend():void{
    if(this.timeInMilliseconds == undefined)
    {
      this.error = "A csúszka segítségével adja meg mennyi ideig legyen aktív a hírdetés!"
    }
    else
    {
      this.extendEvent.emit(this.timeInMilliseconds);
    }
  }

  public remove():void{
    this.deleteEvent.emit(null);
  }

  public valueChanged(change){
    this.error = "";
    this.timeInMilliseconds = (change.value as number)*3600000;
    const timeLeft: Date = new Date(this.timeInMilliseconds);
  }


  constructor() { }

  ngOnInit() {
  }

}
