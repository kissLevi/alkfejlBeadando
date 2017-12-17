import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material';

@Component({
  selector: 'app-write-rating',
  templateUrl: './write-rating.component.html',
  styleUrls: ['./write-rating.component.css']
})
export class WriteRatingComponent implements OnInit {


  @Output() rating = new EventEmitter();

  private error:string = "";

  public rate(rating:number,description:string){
    if(rating != 0){
      this.error = "";
      this.rating.emit({rating,description});
    }
    else
    {
      this.error = "A csúszka mozgatásával adjon meg értéket!";
    }
  }


  constructor() { }

  ngOnInit() {
  }

}
