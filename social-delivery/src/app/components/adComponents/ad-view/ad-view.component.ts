import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { UserService } from '../../../services/user.service';
import { AdService } from '../../../services/ad.service';
import { Ad,Status } from '../../../classes/ad';
import { AuthService } from '../../../services/auth.service';
import { User, Role } from '../../../classes/user';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ad-view',
  templateUrl: './ad-view.component.html',
  styleUrls: ['./ad-view.component.css'],
  providers: [UserService,AdService]
})
export class AdViewComponent implements OnInit {
  private allAds:Ad[];

  private avaliableAds:Ad[];
  private ownAds:Ad[];
  private deliveries:Ad[];

  private selectedIndex = 0;

  public changed(change):void{
    this.selectedIndex = change.index as number;
    let rs :ActivatedRoute;
    
  }

  public ownerOfAd(ad:Ad):boolean{
    if(this.authService.getUser().id == ad.costumer_id.id || this.authService.getUser().role == Role.ADMIN)
    {
      return true;
    }
    return false;
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
      this.selectedIndex = 0;
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
      let index2:number = this.avaliableAds.findIndex(a=>a.id==adId);
      let index3:number = this.allAds.findIndex(a=>a.id==adId);
      if(index2 != -1)
      {
        this.avaliableAds.splice(index2,1);
      }
      if(index3 != -1)
      {
        this.allAds.splice(index3,1);
      }
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
    this.adService.getAllAds().subscribe((ads:Ad[])=>{
      this.allAds = ads;
      this.avaliableAds = ads.filter(ad => ad.status == "PENDING" && ad.deadline> new Date().getTime());
      this.deliveries = ads.filter(ad =>ad.status == "ACCEPTED" && ad.deliver_id.id == UserService.getUser().id) ;
    })
    this.adService.getAdsOfUser().subscribe((ads:Ad[])=>{
      this.ownAds = ads.filter(ad => ad.costumer_id.id == UserService.getUser().id);
    });
    
  }

}
