import { Component, OnInit ,EventEmitter,Output} from '@angular/core';
import { User } from '../../classes/user';

@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.css']
})
export class LoginformComponent implements OnInit {
  @Output()
  public userData: EventEmitter<User> = new EventEmitter();
  
  public clickLogin(name:string, password:string){
    this.userData.emit(new User(name,password));
  }

  constructor() { }

  ngOnInit() {
  }

}
