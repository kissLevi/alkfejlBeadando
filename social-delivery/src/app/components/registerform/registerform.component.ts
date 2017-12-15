import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormControl, Validators } from '@angular/forms';
import { FormChecker } from '../../classes/form-checker';

@Component({
  selector: 'app-registerform',
  templateUrl: './registerform.component.html',
  styleUrls: ['./registerform.component.css'],
  providers: [UserService]
})
export class RegisterformComponent implements OnInit {
  public error: string = "";

  userNameError = new FormChecker("Ilyen felhasználónév már létezik!");

  formControl = new FormControl('', [
    Validators.required,
  ]);

  public clickRegistrate(name:string, password:string){
    this.userService.registrate(name,password).subscribe((status:number)=>{
      if(status == 400){
        this.userNameError.errorStatus = true;
      }
      else if(status == 200){
        this.userNameError.errorStatus = false;
      }
    });
  }
  
  constructor(
    private userService:UserService
  ) { }

  ngOnInit() {
  }

}
