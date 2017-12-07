import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Ad } from '../../classes/ad';
import { AdService } from '../../services/ad.service';

@Component({
  selector: 'app-ad-view',
  templateUrl: './ad-view.component.html',
  styleUrls: ['./ad-view.component.css'],
  providers: [UserService,AdService]
})
export class AdViewComponent implements OnInit {
  private ads:Ad[];

 
  public acceptAd(id:number):void{
    this.adService.acceptAd(id).subscribe(()=>{
      this.adService.getAds().subscribe((ads : Ad[])=>{
        this.ads = ads;
      });
    })
  }

  constructor(
    private userService:UserService,
    private adService:AdService
  ) { }

  ngOnInit() {
    this.adService.getAds().subscribe((ads : Ad[])=>{
      this.ads = ads;
    });
  }

}
