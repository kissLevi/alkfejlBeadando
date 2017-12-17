import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginformComponent } from './components/loginform/loginform.component';
import { UiModule } from './modules/ui/ui.module';
import { RegisterformComponent } from './components/registerform/registerform.component';
import { IntroductionComponent } from './components/introduction/introduction.component';
import { AppRouterModule } from './modules/app-router/app-router.module';
import { HttpClientModule } from '@angular/common/http';
import { MainpageViewComponent } from './components/mainpage-view/mainpage-view.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { UserComponent } from './components/user/user.component';
import { UserViewComponent } from './components/user-view/user-view.component';
import { AdComponent } from './components/adComponents/ad/ad.component';
import { AdViewComponent } from './components/adComponents/ad-view/ad-view.component';
import { NewadComponent } from './components/adComponents/newad/newad.component';
import { RateComponent } from './components/rate/rate.component';
import { RateViewComponent } from './components/rate-view/rate-view.component';
import { AdExtendComponent } from './components/adComponents/ad-extend/ad-extend.component';
import { WriteRatingComponent } from './components/write-rating/write-rating.component';
import { BalanceComponent } from './components/balance/balance.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginformComponent,
    RegisterformComponent,
    IntroductionComponent,
    MainpageViewComponent,
    ToolbarComponent,
    UserComponent,
    UserViewComponent,
    AdComponent,
    AdViewComponent,
    NewadComponent,
    RateComponent,
    RateViewComponent,
    AdExtendComponent,
    WriteRatingComponent,
    BalanceComponent,
  ],
  imports: [
    BrowserModule,
    AppRouterModule,
    UiModule,
    HttpClientModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
