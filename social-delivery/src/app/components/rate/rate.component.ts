import { Component, OnInit,Input, Output,EventEmitter } from '@angular/core';
import { Rating } from '../../classes/rating';


@Component({
  selector: 'app-rate',
  templateUrl: './rate.component.html',
  styleUrls: ['./rate.component.css']
})
export class RateComponent implements OnInit {
  @Input()
  private rating:Rating;

  @Output() newRating = new EventEmitter();  

  public ratingHappened({rating,description}){
    this.newRating.emit({rating,description,id:this.rating.id});
  }



  constructor() { }

  ngOnInit() {
  }

}
