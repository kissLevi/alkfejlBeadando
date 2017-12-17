import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { api } from '../config/api';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { User, Role } from '../classes/user';
import { UserService } from './user.service';

@Injectable()
export class AuthService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public login(username:string,password:string): Observable<number>{
    const result = new Subject<number>();
    this.httpClient.post(api + 'login', { username, password }).subscribe((user) => {
      UserService.setUser( user as User);
      result.next(200);
    }, (error: HttpErrorResponse) => {
      UserService.setUser(null as User);
      result.next(error.status);
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

  public getUser(): User{
    return UserService.getUser();
  }

  public getRole(): Role {
    if (this.isLoggedIn()) {
      return UserService.getUser().role;
    }
    return undefined;
  }

}
