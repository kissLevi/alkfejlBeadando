import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})


export class AppComponent {
  title = 'app';
  constructor (
    private authService: AuthService
  ) {}


  ngOnInit() {
    this.authService.syncLoginStatus();
  }
}

