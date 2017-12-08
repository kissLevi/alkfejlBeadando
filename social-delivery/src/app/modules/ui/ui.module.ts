import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatListModule,MatGridList, MatGridListModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule,MatToolbarModule, MatProgressSpinnerModule,MatExpansionModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [BrowserAnimationsModule,MatListModule,MatGridListModule,CommonModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule,MatToolbarModule,ReactiveFormsModule,MatProgressSpinnerModule,MatExpansionModule],
  exports: [MatButtonModule,MatListModule,MatGridListModule,MatCardModule,MatInputModule,MatTabsModule,MatToolbarModule,ReactiveFormsModule,MatProgressSpinnerModule,MatExpansionModule],
  declarations: []
})
export class UiModule { }
