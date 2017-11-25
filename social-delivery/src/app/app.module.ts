import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginformComponent } from './components/loginform/loginform.component';
import { UiModule } from './modules/ui/ui.module';
import { RegisterformComponent } from './components/registerform/registerform.component';
import { IntroductionComponent } from './components/introduction/introduction.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginformComponent,
    RegisterformComponent,
    IntroductionComponent,
  ],
  imports: [
    BrowserModule,
    UiModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
