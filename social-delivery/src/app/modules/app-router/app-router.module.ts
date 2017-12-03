import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainpageViewComponent } from '../../components/mainpage-view/mainpage-view.component';
import { UserComponent } from '../../components/user/user.component';


const appRoutes: Routes = [
  { path: '', component: MainpageViewComponent },
  { path: 'user', component: UserComponent },
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