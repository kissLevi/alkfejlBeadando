import { Component, OnInit } from '@angular/core';
import { AdService } from '../../services/ad.service';
import { User } from '../../classes/user';
import { Ad } from '../../classes/ad';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-mainpage-view',
  templateUrl: './mainpage-view.component.html',
  styleUrls: ['./mainpage-view.component.css'],
  providers : [AdService,AuthService]
})
export class MainpageViewComponent implements OnInit {
  private user:User;
  private _ads: Ad[];

  public tryLogin(user:User){
    this.user = user;
  }
  public logout(){
    this.authService.logout();
  }
  constructor(
    private authService : AuthService,
    private adService : AdService
  ) { }

  ngOnInit() {
    this.authService.syncLoginStatus();


    // this.adService.getAds().subscribe((ads: Ad[]) => {
    //   this._ads = ads;
    // });
  }

}
