import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-registerform',
  templateUrl: './registerform.component.html',
  styleUrls: ['./registerform.component.css'],
  providers: [UserService]
})
export class RegisterformComponent implements OnInit {

  public clickRegistrate(name:string, password:string){
  }

  constructor(
    private userService:UserService
  ) { }

  ngOnInit() {
  }

}
