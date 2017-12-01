import { Component, OnInit ,EventEmitter,Output} from '@angular/core';
import { User } from '../../classes/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css'],
  providers: [AuthService]
})
export class LoginformComponent implements OnInit {
  public error: string = "";  
  
  public clickLogin(name:string, password:string){
    this.authService.login(name, password).subscribe((success: boolean) => {
      if (success) {

      } else {
        this.error = 'Hibás felhasnzálónév vagy jelszó';
      }
    });
  }

  constructor(
    private authService:AuthService
  ) { }

  ngOnInit() {
  }

}
