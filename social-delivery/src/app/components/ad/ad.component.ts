import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import { Ad } from '../../classes/ad';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Component({
  selector: 'app-ad',
  templateUrl: './ad.component.html',
  styleUrls: ['./ad.component.css']
})
export class AdComponent implements OnInit {

  @Input()
  private ad:Ad;

  @Input()
  private owner:boolean;

  @Output() acceptEvent = new EventEmitter();
  
  @Output() extend= new EventEmitter();
  
  @Output() delete= new EventEmitter();
  
  // public owner():boolean{
  //   return this.ad.costumer_id == this.userId;
  // }

  public deleteEvent():void{
    this.delete.emit(this.ad.id);
  }

  public extendEvent(date :string):void{
    this.extend.emit({date:date,id:this.ad.id});
  }

  public haveToUpdate():boolean{
    return this.ad.deadline < new Date().getTime();
  }

  public getDate():Date{
    const d: Date = new Date(this.ad.deadline);
    return d;
  }
  public accept(): void{
    this.acceptEvent.emit(this.ad.id);
  }

  constructor() { }

  ngOnInit() {
  }

}
