import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainpageViewComponent } from '../../components/mainpage-view/mainpage-view.component';
import { UserComponent } from '../../components/user/user.component';
import { UserViewComponent } from '../../components/user-view/user-view.component';
import { RateViewComponent } from '../../components/rate-view/rate-view.component';

import { RateComponent } from '../../components/rate/rate.component';
import { AdViewComponent } from '../../components/adComponents/ad-view/ad-view.component';
import { RouteGuardService } from '../../services/route-guard.service';
import { AuthService } from '../../services/auth.service';
import { BalanceComponent } from '../../components/balance/balance.component';


const appRoutes: Routes = [
  { path: '', canActivateChild: [RouteGuardService], children: [
    { path: '', component: MainpageViewComponent, pathMatch: 'full' },
    { path: 'user', component: UserViewComponent,data: { roles: ['USER', 'ADMIN'] }},
    { path: 'ads', component: AdViewComponent,data: { roles: ['USER', 'ADMIN'] }},
    { path: 'ads/:id', component: AdViewComponent,data: { roles: ['USER', 'ADMIN'] }},
    { path: 'ads/extend', component: AdViewComponent,data: { roles: ['USER', 'ADMIN'] }},
    { path: 'ads/own', component: AdViewComponent,data: { roles: ['USER', 'ADMIN'] }},
    { path: 'ads/accepted', component: AdViewComponent,data: { roles: ['USER', 'ADMIN'] }},
    { path: 'balance', component : BalanceComponent,data: { roles: ['ADMIN'] }}
    // { path: 'rate', component : RateComponent,data: { roles: ['USER', 'ADMIN'] }}
  ]}
];
// =======
// import { BalanceComponent } from '../../components/balance/balance.component';


// const appRoutes: Routes = [
//   { path: '', component: MainpageViewComponent, pathMatch: 'full' },
//   { path: 'user', component: UserViewComponent },
//   { path: 'ads', component: AdViewComponent},
//   { path: 'rate', component : RateComponent},
//   { path: 'balance', component : BalanceComponent}
// >>>>>>> 92bed5993071034a0f536396fda51922bbce0ac8
// ];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  declarations: [],
  providers: [RouteGuardService, AuthService]
})
export class AppRouterModule { }