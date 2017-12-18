import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../classes/user';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
  providers: [UserService]
})
export class UserEditComponent implements OnInit {

  private users:User[];

  constructor(
    private userService:UserService
  ) { }

  public delete(id:number):void
  {
    this.userService.deleteUser(id).subscribe((status)=>{
      if(status == 200)
      {
        let index = this.users.findIndex(user => user.id == id);
        this.users.splice(index,1);
      }
    })
  }

  ngOnInit() {
    this.userService.getAllUsers().subscribe((users)=>{
      this.users = users as User[];
    })
  }

}
