import { Component, OnInit } from '@angular/core';
import { User } from '../../classes/user';
import { BalanceService } from '../../services/balance.service';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css'],
  providers: [BalanceService]
})
export class BalanceComponent implements OnInit {
  user : User;
  users: User[];

  constructor(
    private balanceService: BalanceService
  ) {}

  ngOnInit() {
    this.balanceService.getUsers().subscribe((users)=>{
      this.users = (users as User[]);
      console.log(this.users);
    })
  }

  public addBalance(id:number,sum:number):void{
    if(id != null && sum>0){
      this.balanceService.addBalance(id,sum).subscribe();
    }
  }

}
