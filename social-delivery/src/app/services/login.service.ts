import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../classes/user';
import { api } from '../config/api';

@Injectable()
export class LoginService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public login(user:User):Observable<any>{
    return this.httpClient.post(api + "login",user);
  }

  public checkLog(): Observable<any>{
    return this.httpClient.get(api + "login");
  }

}
