import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Ad } from '../../classes/ad';
import { AdService } from '../../services/ad.service';
import { AuthService } from '../../services/auth.service';
import { User } from '../../classes/user';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Component({
  selector: 'app-ad-view',
  templateUrl: './ad-view.component.html',
  styleUrls: ['./ad-view.component.css'],
  providers: [UserService,AdService]
})
export class AdViewComponent implements OnInit {
  private allAds:Ad[];
  private ownAds:Ad[];
  private deliveries:Ad[];

  private _ownerAds = new BehaviorSubject<Ad[]>([]);


  public ownerOfAd(ad:Ad):boolean{
    return this.authService.getUser().id == ad.costumer_id;
  }
  
  public acceptAd(id:number):void{
    this.adService.acceptAd(id).subscribe(()=>{
      this.adService.getAllAvailableAds().subscribe((ads : Ad[])=>{
        this.allAds = ads;
      });
    })
  }

  public extend(date){
    const newDate: number = new Date().getTime()+ date.date as number;
    const id:number = date.id;
    let newAd:Ad = this.ownAds.find(a=>a.id == id);
    this.allAds.push(newAd);
    newAd.deadline =  parseInt((newDate as number).toFixed(0))
    this.adService.updateAd(newAd).subscribe((newAd)=>{
    }); 
  }

  public delete(adId:number){
    let index:number = this.ownAds.findIndex(a=>a.id==adId);
    this.adService.deleteAd(adId).subscribe(()=>{
      this.ownAds.splice(index,1);
    })
  }

  public deliverAd(adData:{delivered:boolean,id:number}){
    let index:number = this.ownAds.findIndex(a=>a.id==adData.id);
    if(adData.delivered){
      this.adService.completed(adData.id).subscribe((result)=>{
        this.ownAds.splice(index,1);
        console.log(result);
      })
    }
    else if(!adData.delivered){
      this.adService.failedToComplete(adData.id).subscribe((result)=>{
        this.ownAds.splice(index,1);
        console.log(result);
      })
    }
  }

  constructor(
    private adService:AdService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.authService.syncLoginStatus();
    this._ownerAds.subscribe((ads :Ad[])=>{
      this.ownAds = ads.filter(ad => ad.costumer_id == UserService.getUser().id);
      this.deliveries = this.ownAds
    })

    this.adService.getAllAvailableAds().subscribe((ads : Ad[])=>{
      this.allAds = ads;
    });
    this.adService.getAdsOfUser().subscribe((ads:Ad[])=>{
      this._ownerAds.next(ads);
    });
    
  }

}
