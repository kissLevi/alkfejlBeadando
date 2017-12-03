import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatListModule,MatGridList, MatGridListModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule,MatToolbarModule, MatProgressSpinnerModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [BrowserAnimationsModule,MatListModule,MatGridListModule,CommonModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule,MatToolbarModule,ReactiveFormsModule,MatProgressSpinnerModule],
  exports: [MatButtonModule,MatListModule,MatGridListModule,MatCardModule,MatInputModule,MatTabsModule,MatToolbarModule,ReactiveFormsModule,MatProgressSpinnerModule],
  declarations: []
})
export class UiModule { }
