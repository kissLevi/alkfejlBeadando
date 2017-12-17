import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Ad } from '../../../classes/ad';
import { User } from '../../../classes/user';

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

  @Input()
  private showRating:User;

  @Output() acceptEvent = new EventEmitter();

  @Output() extend= new EventEmitter();
  
  @Output() delete= new EventEmitter();

  @Output() delivered= new EventEmitter();
  
  public successfulyDelivered(delivered:boolean):void{
    this.delivered.emit({delivered:delivered,id:this.ad.id});
  }
  public notDelivered():void{

  }


  public deleteEvent():void{
    this.delete.emit(this.ad.id);
  }

  public extendEvent(date :string):void{
    this.extend.emit({date:date,id:this.ad.id});
  }


  public haveToUpdate():boolean{
    return this.ad.deadline < new Date().getTime();
  }

  public timeleft():string{
    const timeLeft: Date = new Date(this.ad.deadline - new Date().getTime());
    const d:string = ((this.ad.deadline - new Date().getTime())/86400000).toFixed(0);
    const h:string = (timeLeft.getHours()-1).toString();
    const m:string = timeLeft.getMinutes().toString();
    return  (d == "0"?"" : d + " nap ")+ (h=="0"?"":h + " óra ") + (m=="0"? "":m +" perc")
  }
  public accept(): void{
    this.acceptEvent.emit(this.ad.id);
  }

  constructor() { }

  ngOnInit() {
  }

}
