import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSelectModule,MatListModule, MatGridList, MatGridListModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule,MatToolbarModule, MatProgressSpinnerModule,MatExpansionModule,MatProgressBarModule,MatDatepickerModule,MatNativeDateModule,MatSliderModule,} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  imports: [MatSelectModule,BrowserAnimationsModule,MatListModule,MatGridListModule,CommonModule,MatCardModule,MatInputModule,MatButtonModule,MatTabsModule,MatToolbarModule,ReactiveFormsModule,MatProgressSpinnerModule,MatExpansionModule,MatProgressBarModule,MatDatepickerModule,MatNativeDateModule,MatSliderModule],
  exports: [MatSelectModule,MatButtonModule,MatListModule,MatGridListModule,MatCardModule,MatInputModule,MatTabsModule,MatToolbarModule,ReactiveFormsModule,MatProgressSpinnerModule,MatExpansionModule,MatProgressBarModule,MatDatepickerModule,MatNativeDateModule,MatSliderModule],
  declarations: []
})
export class UiModule { }
 