import { Component, OnInit ,EventEmitter,Output} from '@angular/core';
import { User } from '../../classes/User';
import { FormChecker } from '../../classes/form-checker';
import { AuthService } from '../../services/auth.service';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';




@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css'],
  providers: [AuthService]
})
export class LoginformComponent implements OnInit {
  

  private userNameError = new FormChecker("Rossz felhasználónév!");
  private passwordError = new FormChecker("Hibás jelszó!");

  private error:string;

  public clickLogin(name:string, password:string){
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
      console.log("asd");
      this.authService.login(name, password).subscribe((status: number) => {
        if (status != 200) {
          if(status == 400){
            this.userNameError.errorStatus = true;
            this.userNameError.errorMsg = "Rossz felhasználónév!";
            this.passwordError.errorStatus = false;
          }
          else if( status == 401){
            this.userNameError.errorStatus = false;
            this.passwordError.errorStatus = true;
            this.passwordError.errorMsg = "Hibás jelszó!";
          }
        }
        else{
          const redirectTo: string = this.route.snapshot.queryParamMap.get('from') || '/ads';
          this.router.navigate([redirectTo]);
        }
      });
    }
    
  }
  formControl = new FormControl('', [
    Validators.required,
  ]);
  
  

  constructor(
    private authService:AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if (this.route.snapshot.queryParamMap.get('from')) {
      this.error = 'Az adott oldal eléréséhez bejelentkezés szükséges!';
    }
  }

}
