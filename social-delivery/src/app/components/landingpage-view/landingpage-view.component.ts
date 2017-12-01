import { Component, OnInit } from '@angular/core';
import { User } from '../../classes/user';
import { HttpErrorResponse } from '@angular/common/http';
import { AdService } from '../../services/ad.service';
import { Ad } from '../../classes/ad';

@Component({
  selector: 'app-landingpage-view',
  templateUrl: './landingpage-view.component.html',
  styleUrls: ['./landingpage-view.component.css'],
  providers : [AdService]
})
export class LandingpageViewComponent implements OnInit {
  private user:User;
  private _ads: Ad[];

  public tryLogin(user:User){
    this.user = user;
  }
  constructor(

     private adService : AdService
  ) { }

  ngOnInit() {
    // this.adService.getAds().subscribe((ads: Ad[]) => {
    //   this._ads = ads;
    // });
  }

}
