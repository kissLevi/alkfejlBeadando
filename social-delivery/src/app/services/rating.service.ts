import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';
import { Subject } from 'rxjs/Subject';
import { Rating } from '../classes/rating';

@Injectable()
export class RatingService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public getRatings():Observable<Rating[]>{
    const result = new Subject<Rating[]>();
    this.httpClient.get(api + "ratings/available").subscribe((ratings)=>{
      console.log(ratings)
      result.next(ratings as Rating[]);
    });

    return result;
  }

  public getOwnRatings():Observable<any>{
    return this.httpClient.get(api+"/ratings");
  }

  public getPendingRatings():Observable<any>{
    return this.httpClient.get(api+"/ratings/available");
  }

  public rate({rating,description,id}):Observable<any>{
    return this.httpClient.put(api + "ratings/available/"+id,{description,rating})
  }

}
