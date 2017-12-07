import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import { Ad } from '../../classes/ad';

@Component({
  selector: 'app-ad',
  templateUrl: './ad.component.html',
  styleUrls: ['./ad.component.css']
})
export class AdComponent implements OnInit {
  @Input()
  private ad:Ad;

  @Output()
  private _id:number
  private _acceptEvent = new EventEmitter();
  
  public get event():EventEmitter<{}>{
    return this._acceptEvent;
  }
  
  public accept(): void{
    this._acceptEvent.emit(null);
  }

  public id():number{
    return this._id;
  }

  constructor() { }

  ngOnInit() {
  }

}
