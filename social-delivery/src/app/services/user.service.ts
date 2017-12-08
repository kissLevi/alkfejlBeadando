import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../classes/user';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { api } from '../config/api';
import { $ } from 'protractor';

@Injectable()
export class UserService {
  private static user: User = null;
  constructor(
    private httpClien: HttpClient,
  ) { }

  public static setUser(user:User){
    this.user=user;
  }

  public static getUser():User{
    return this.user;
  }

  public registrate(username:string,password:string):Observable<number>{
    const result = new Subject<number>();
    this.httpClien.post(api + "register",{username,password}).subscribe(()=>{
      result.next(200);
    }, (error) => {
      result.next(400);
    });
    return result; 
  }

  public getUserProfile(id: number):Observable<any>{
    return this.httpClien.get(api + 'user/' + id)
  }

  public updateUserProfile(id: number, un: string, pw: string):Observable<any>{
    return this.httpClien.put(api + "user/" + id,
    {
      "username": un,
      "password": pw
   }
);
  }

}
