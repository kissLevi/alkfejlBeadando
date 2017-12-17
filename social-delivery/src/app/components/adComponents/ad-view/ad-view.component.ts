import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { UserService } from '../../../services/user.service';
import { AdService } from '../../../services/ad.service';
import { Ad,Status } from '../../../classes/ad';
import { AuthService } from '../../../services/auth.service';
import { User } from '../../../classes/user';

@Component({
  selector: 'app-ad-view',
  templateUrl: './ad-view.component.html',
  styleUrls: ['./ad-view.component.css'],
  providers: [UserService,AdService]
})
export class AdViewComponent implements OnInit {
  private avaliableAds:Ad[];
  private ownAds:Ad[];
  private deliveries:Ad[];

  private _ownerAds = new BehaviorSubject<Ad[]>([]);

  private selectedIndex = 0;

  public changed(change):void{
    this.selectedIndex = change.index as number;
  }

  public ownerOfAd(ad:Ad):boolean{
    return this.authService.getUser().id == ad.costumer_id;
  }

  public getUser():User{
    return this.authService.getUser();
  } 
  
  public acceptAd(adId:number):void{
    this.adService.acceptAd(adId).subscribe(()=>{
      let ad:Ad = this.avaliableAds.find(a=>a.id == adId);
      ad.status = Status.ACCEPTED;
      let index:number = this.avaliableAds.findIndex(a=>a.id==adId);
      
      this.avaliableAds.splice(index,1);
      this.deliveries.push(ad);
    })
  }

  public newAd(ad:Ad){
    this.adService.addAd(ad).subscribe((newAd)=>{
      this.avaliableAds.push(newAd);
      this.ownAds.push(newAd);
    })
  }

  public extend(date){
    const newDate: number = new Date().getTime()+ date.date as number;
    const id:number = date.id;
    let newAd:Ad = this.ownAds.find(a=>a.id == id);
    this.avaliableAds.push(newAd);
    newAd.deadline =  parseInt((newDate as number).toFixed(0))
    this.adService.updateAd(newAd).subscribe((newAd)=>{
    }); 
  }

  public delete(adId:number){
    this.adService.deleteAd(adId).subscribe(()=>{
      let index:number = this.ownAds.findIndex(a=>a.id==adId);
      this.ownAds.splice(index,1);
    })
  }

  public deliverAd(adData:{delivered:boolean,id:number}){
    let index:number = this.ownAds.findIndex(a=>a.id==adData.id);
    if(adData.delivered){
      this.adService.completed(adData.id).subscribe((result)=>{
        this.ownAds.splice(index,1);
      })
    }
    else if(!adData.delivered){
      this.adService.failedToComplete(adData.id).subscribe((result)=>{
        this.ownAds.splice(index,1);
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
    })
    this.adService.getAllAds().subscribe((ads:Ad[])=>{
      this.avaliableAds = ads.filter(ad => ad.status == "PENDING" && ad.deadline> new Date().getTime());
      this.deliveries = ads.filter(ad =>ad.status == "ACCEPTED" && ad.deliver_id == UserService.getUser().id) ;
    })
    this.adService.getAdsOfUser().subscribe((ads:Ad[])=>{
      this._ownerAds.next(ads);
    });
    
  }

}
