import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginformComponent } from './components/loginform/loginform.component';
import { UiModule } from './modules/ui/ui.module';
import { RegisterformComponent } from './components/registerform/registerform.component';
import { IntroductionComponent } from './components/introduction/introduction.component';
import { LandingpageViewComponent } from './components/landingpage-view/landingpage-view.component';
import { AppRouterModule } from './modules/app-router/app-router.module';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    LoginformComponent,
    RegisterformComponent,
    IntroductionComponent,
    LandingpageViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRouterModule,
    UiModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
