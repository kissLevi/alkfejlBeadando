import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Ad } from '../classes/ad';
import { api } from '../config/api';

@Injectable()
export class AdService {

  constructor(
    private httpClient: HttpClient
  ) {}

  // public getAds(): Observable<Ad[]> {
  //   return this.httpClient.get(api + 'ads');
  // }

}
