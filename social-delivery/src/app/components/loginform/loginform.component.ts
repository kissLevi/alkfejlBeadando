import { Component, OnInit ,EventEmitter,Output} from '@angular/core';
import { User } from '../../classes/User';
import { FormChecker } from '../../classes/form-checker';
import { AuthService } from '../../services/auth.service';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { Router } from '@angular/router';




@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css'],
  providers: [AuthService]
})
export class LoginformComponent implements OnInit {
  

  userNameError = new FormChecker("Rossz felhasználónév!");
  passwordError = new FormChecker("Hibás jelszó!");

  public clickLogin(name:string, password:string){
    this.authService.login(name, password).subscribe((status: number) => {
        if (status != 200) {
          if(status == 400){
            this.userNameError.errorStatus = true;
          }
          else if( status == 401){
            this.userNameError.errorStatus = false;
            this.passwordError.errorStatus = true;
          }
        }
        else{
          this.router.navigate(['/ads']);
        }
      });
  }
  formControl = new FormControl('', [
    Validators.required,
  ]);
  
  

  constructor(
    private authService:AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }

}
