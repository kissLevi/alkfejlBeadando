import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { api } from '../config/api';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { User } from '../classes/user';
import { UserService } from './user.service';

@Injectable()
export class AuthService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public login(username:string,password:string): Observable<boolean>{
    const result = new Subject<boolean>();
    this.httpClient.post(api + 'login', { username, password }).subscribe((user) => {
      UserService.setUser( user as User);
      result.next(true);
    }, (error) => {
      UserService.setUser(null as User);
      result.next(false);
    });
    return result;
  }

  public logout(): void{
    this.httpClient.get(api + 'logout').subscribe(()=> {
      UserService.setUser(null);
    });
  }

  public syncLoginStatus(): void {
    this.httpClient.get(api + 'login').subscribe((user) => {
      if (user) {
        UserService.setUser(user as User);
      } else {
        UserService.setUser(null);
      }
    });
  }
  public isLoggedIn(): boolean {
    return UserService.getUser() !== null;
  }

}
