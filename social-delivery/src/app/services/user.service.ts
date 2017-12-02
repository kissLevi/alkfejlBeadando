import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../classes/user';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { api } from '../config/api';

@Injectable()
export class UserService {
  private static user: User = null;
  constructor(
    private httpClien: HttpClient
  ) { }

  public static setUser(user:User){
    this.user=user;
  }

  public static getUser():User{
    return this.user;
  }

  public registrate(username:string,password:string):Observable<boolean>{
    const result = new Subject<boolean>();
    this.httpClien.post(api + "register",{username,password}).subscribe(()=>{
      result.next(true);
    }, (error) => {
      result.next(false);
    });
    return result; 
  }

}
