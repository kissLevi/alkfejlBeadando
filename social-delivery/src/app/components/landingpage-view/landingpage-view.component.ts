import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { User } from '../../classes/user';
import { HttpErrorResponse } from '@angular/common/http';
import { AdService } from '../../services/ad.service';
import { Ad } from '../../classes/ad';

@Component({
  selector: 'app-landingpage-view',
  templateUrl: './landingpage-view.component.html',
  styleUrls: ['./landingpage-view.component.css'],
  providers : [LoginService,AdService]
})
export class LandingpageViewComponent implements OnInit {
  private user:User;
  private _ads: Ad[];

  public tryLogin(user:User){
    this.user = user;
    this.loginService.login(this.user).subscribe(
      (response)=>{
        
        console.log(user);
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          console.log('An error occurred:', err.error.message);
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
        }
      }
    
    )
  }
  constructor(
     private loginService : LoginService,
     private adService : AdService
  ) { }

  ngOnInit() {
    this.loginService.checkLog().subscribe(
      (response)=>{
        this.user = response;
        console.log(this.user);
      }
    );
    this.adService.getAds().subscribe((ads: Ad[]) => {
      this._ads = ads;
    });
  }

}
