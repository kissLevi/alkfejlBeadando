import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-registerform',
  templateUrl: './registerform.component.html',
  styleUrls: ['./registerform.component.css'],
  providers: [UserService]
})
export class RegisterformComponent implements OnInit {
  public error: string = "";

  public clickRegistrate(name:string, password:string){
    this.userService.registrate(name,password).subscribe((sucess:boolean)=>{
      if(!sucess){
        this.error = "Ilyen felhasználó már létezik";
      }
    });
  }

  constructor(
    private userService:UserService
  ) { }

  ngOnInit() {
  }

}
