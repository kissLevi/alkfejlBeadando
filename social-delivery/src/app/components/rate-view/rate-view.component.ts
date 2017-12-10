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
  

  private pendingRatings:Rating[];
  private ownRatings:Rating[];

  public rate({rating,description,id}){
    this.ratingService.rate({rating,description,id}).subscribe((result)=>{
      let index:number = this.pendingRatings.findIndex(a=>a.id==id);
      this.pendingRatings.splice(index,1);
    })
    
  }



  constructor(
    private ratingService:RatingService,
  ) { }

  ngOnInit() {
    this.ratingService.getOwnRatings().subscribe((ratings)=>{
      this.ownRatings = (ratings as Rating[]);
    })

    this.ratingService.getPendingRatings().subscribe((ratings)=>{
      this.pendingRatings = (ratings as Rating[]);
    })
  }

}
