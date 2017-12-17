import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { UserService } from './services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})


export class AppComponent {
  title = 'app';
  constructor (
    private authService: AuthService,
    private router: Router
  ) {}
  
  private loginChecked:boolean;

  ngOnInit() {
    this.authService.syncLoginStatus().subscribe((status)=>{
      this.loginChecked = true;
      if(status){
        this.router.navigate(['/ads']);
      }
      else if(!status){
        
      }
    });
    
  }
}

