import { Component, OnInit, Input } from '@angular/core';
//import { User } from '../../classes/user-data';
import { User } from '../../classes/user';
import {Router, ActivatedRoute} from '@angular/router';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [UserService, AuthService]
})
export class UserComponent implements OnInit {
  @Input()
  public user: User;
  
  private editable: boolean;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private authService: AuthService
  ) { this.editable=false; }

  public edit(): void{
    this.editable = !this.editable;
  }

  ngOnInit() {
    // if(this.user!=null){
    //   this.user = UserService.getUser();
    //   console.log(this.user); 
    //   let user: User = this.user;        
    //   this.userService.getUserProfile(user.id).subscribe((user) => {
    //     this.user = user;
    //   });
    // }else{
    //   this.authService.syncLoginStatus();
    //   this.user = UserService.getUser();
    // }   
  }

  public clickEdit(username: string, pw:string): void{
    this.userService.updateUserProfile(this.user.id,username,pw).subscribe((user) =>{
      this.user = user;
    });
  }

}