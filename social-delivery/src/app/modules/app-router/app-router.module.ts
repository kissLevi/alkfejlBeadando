import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingpageViewComponent } from '../../components/landingpage-view/landingpage-view.component';


const appRoutes: Routes = [
  { path: '', component: LandingpageViewComponent },
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