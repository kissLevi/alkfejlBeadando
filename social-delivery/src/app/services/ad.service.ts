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

  public getAllAvailableAds(): Observable<Ad[]> {
    const result = new Subject<Ad[]>();
    this.httpClient.get(api + "ads").subscribe((ads)=>{
      result.next((ads as Ad[]).filter(ad => ad.status == "PENDING" && ad.deadline> new Date().getTime()));
    })
    return result;
  }

  public getAdsOfUser(): Observable<Ad[]> {
    const result = new Subject<Ad[]>();
    this.httpClient.get(api + "user/ads").subscribe((ads)=>{
      result.next((ads as Ad[]));
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

}


// const result = new Subject<number>();
// this.httpClient.post(api + 'login', { username, password }).subscribe((user) => {
//   UserService.setUser( user as User);
//   result.next(200);
// }, (error: HttpErrorResponse) => {
//   UserService.setUser(null as User);
//   result.next(error.status);
// });
// return result;