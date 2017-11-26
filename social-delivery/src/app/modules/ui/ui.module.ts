import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatListModule,MatGridList, MatGridListModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  imports: [BrowserAnimationsModule,MatListModule,MatGridListModule,CommonModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule],
  exports: [MatButtonModule,MatListModule,MatGridListModule,MatCardModule,MatInputModule,MatTabsModule],
  declarations: []
})
export class UiModule { }
