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

  private errors:string[] =[];

  constructor(
    private balanceService: BalanceService
  ) {}

  ngOnInit() {
    this.balanceService.getUsers().subscribe((users)=>{
      this.users = (users as User[]);
      console.log(this.users);
    })
  }

  public addBalance(selected,sum:number):void{
    this.errors = [];
    if(sum==0)
    {
      this.errors.push("Adja meg mennyit szeretne befizetni!");
    }
    if(selected == null)
    {
      this.errors.push("Válasszon ki egy felhasználót!");
    }
    if(selected != null && sum>0){
      this.balanceService.addBalance(selected.id,sum).subscribe();
    }
  }

}
