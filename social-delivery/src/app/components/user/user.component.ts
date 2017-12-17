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

  private errors:string[] =[];
  
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
  }

  public clickEdit(username: string, pw:string): void{
    if(username == "" && pw == "")
    {
      this.errors = [];
      this.errors.push("Új jelszó vagy felhasználónév megadása kötelező!");
    }
    else
    {
      username = username == "" ? this.user.username : username;
      this.userService.updateUserProfile(this.user.id,username,pw).subscribe((user) =>{
        if (user == "400")
        {
          this.errors = [];
          this.errors.push("Ilyen felhasználónévvel már létezik felhasználó!")
        }
        else
        {
          this.errors = [];
          this.user = user;
          this.editable = false;
        }

      });

    }
    
  }

}
