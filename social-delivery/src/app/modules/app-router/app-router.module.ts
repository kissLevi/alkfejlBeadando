import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainpageViewComponent } from '../../components/mainpage-view/mainpage-view.component';
import { UserComponent } from '../../components/user/user.component';
import { UserViewComponent } from '../../components/user-view/user-view.component';
import { RateViewComponent } from '../../components/rate-view/rate-view.component';

import { RateComponent } from '../../components/rate/rate.component';
import { AdViewComponent } from '../../components/adComponents/ad-view/ad-view.component';
import { BalanceComponent } from '../../components/balance/balance.component';


const appRoutes: Routes = [
  { path: '', component: MainpageViewComponent, pathMatch: 'full' },
  { path: 'user', component: UserViewComponent },
  { path: 'ads', component: AdViewComponent},
  { path: 'rate', component : RateComponent},
  { path: 'balance', component : BalanceComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRouterModule { }