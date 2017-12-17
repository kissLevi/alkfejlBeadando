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
  public success: boolean = false;

  userNameError = new FormChecker("Ilyen felhasználónév már létezik!");
  passwordError = new FormChecker("Hibás jelszó!");

  formControl = new FormControl('', [
    Validators.required,
  ]);

  public clickRegistrate(name:string, password:string){
    this.success=false;
    if(name == "" || password =="")
    {
      this.userNameError.errorStatus = false;
      this.passwordError.errorStatus = false;
      if(name == "")
      {
        this.userNameError.errorStatus = true;
        this.userNameError.errorMsg = "A felhasználónév megadása kötelező";
      }
      if(password == "")
      {
        this.passwordError.errorStatus = true;
        this.passwordError.errorMsg = "A jelszó megadása kötelező";
      }
    }
    else
    {
      this.userService.registrate(name,password).subscribe((status:number)=>{
        if(status == 400){
          this.userNameError.errorStatus = true;
        }
        else if(status == 200){
          this.userNameError.errorStatus = false;
          this.passwordError.errorStatus = false;
          this.success = true;
          console.log("ASD");
        }
      });
    }
  }
  
  constructor(
    private userService:UserService
  ) { }

  ngOnInit() {
  }

}
