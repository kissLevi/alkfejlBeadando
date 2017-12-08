import { Component, OnInit } from '@angular/core';
import { RatingService } from '../../services/rating.service';
import { Rating } from '../../classes/rating';

@Component({
  selector: 'app-rate-view',
  templateUrl: './rate-view.component.html',
  styleUrls: ['./rate-view.component.css'],
  providers: [RatingService]
})
export class RateViewComponent implements OnInit {

  constructor(
    private ratingService:RatingService,
    private ratings:Rating[]
  ) { }

  ngOnInit() {
    this.ratingService.getRatings().subscribe((ratings)=>{
      console.log(ratings as Rating[])
      // this.ratings = ratings as Rating[];
      // console.log(this.ratings);
    })
  }

}
