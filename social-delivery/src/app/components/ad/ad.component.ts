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

  @Output() acceptEvent = new EventEmitter();
  
  public accept(): void{
    this.acceptEvent.emit(this.ad.id);
  }

  constructor() { }

  ngOnInit() {
  }

}
