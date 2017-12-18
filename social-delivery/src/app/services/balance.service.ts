import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { api } from '../config/api';

@Injectable()
export class BalanceService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public addBalance(id: number, sum: number):Observable<any>{
    return this.httpClient.post(api+"user/"+id+"/balance", sum);
  }
}
