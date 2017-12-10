import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Ad } from '../classes/ad';
import { api } from '../config/api';
import { Subject } from 'rxjs/Subject';


@Injectable()
export class AdService {
  

  constructor(
    private httpClient: HttpClient
  ) {}

  public getAdsOfUser(): Observable<Ad[]> {
    const result = new Subject<Ad[]>();
    this.httpClient.get(api + "user/ads").subscribe((ads)=>{
      result.next((ads as Ad[]));
    })
    return result;
  }

  public getAllAds():Observable<Ad[]>{
    const result = new Subject<Ad[]>();
    this.httpClient.get(api + "ads").subscribe((ads)=>{
      result.next(ads as Ad[]);
    })
    return result;
  }

  public acceptAd(id:number): Observable<any>{
    return this.httpClient.put(api + "ads/"+id+"/accept",{});
  }

  public deleteAd(id:number): Observable<any>{
    return this.httpClient.delete(api+"/ads/"+id);
  }

  public updateAd(ad:Ad): Observable<any>{
    return this.httpClient.put(api+"/ads/"+ad.id,ad);
  }

  public completed(id:number):Observable<any>{
    return this.httpClient.put(api + "/ads/"+id+"/complete",{})
  }

  public failedToComplete(id:number):Observable<any>{
    return this.httpClient.put(api + "/ads/"+id+"/failedTocomplete",{})
  }

  public addAd(ad:Ad):Observable<Ad>{
    const result = new Subject<Ad>();
    this.httpClient.post(api + "ads",{name:ad.name,description:ad.description,location:ad.location,price:ad.price,deadline:ad.deadline}).subscribe((newAd)=>{
      result.next(newAd as Ad);
    })
    return result;

  }

}