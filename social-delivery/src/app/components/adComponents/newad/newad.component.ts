import { Component, OnInit,Output,EventEmitter, Input } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material';
import { Ad } from '../../../classes/ad';
import { User } from '../../../classes/user';


@Component({
  selector: 'app-newad',
  templateUrl: './newad.component.html',
  styleUrls: ['./newad.component.css']
})
export class NewadComponent implements OnInit {

  constructor() { }
  
  @Input()
  private user:User;
  
  private _newAd = new EventEmitter();
  
  private errors:string[] = [];
  
  @Output()
  public get newAd():EventEmitter<any>{
    return this._newAd;
  }



  public makeOrder(name:string,description:string,location:string,price:number,deadline:number):void{
    this.errors = [];
    console.log(deadline);
    if(name == "")
    {
      this.errors.push("A termék nevének megadása kötelező!")
    }
    if(location == "")
    {
      this.errors.push("A cím megadása kötelező!")
    }
    if(price == 0)
    {
      this.errors.push("Az ár megadása kötelező!")
    }
    if(deadline == 0 )
    {
      this.errors.push("A határidő megadása kötelező!")
    }
    if(price > this.user.balance)
    {
      this.errors.push("Az ár nem lehet magasabb mint a rendelkezésre álló pénze!")
    }
    else{
      let deadl:number =  new Date().getTime()+ (deadline *3600000);
      let newAd:Ad = new Ad(name,description,location,price,deadl);
      this._newAd.emit(newAd);
    }


  }

  ngOnInit() {
  }

}
