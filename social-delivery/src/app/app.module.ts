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
