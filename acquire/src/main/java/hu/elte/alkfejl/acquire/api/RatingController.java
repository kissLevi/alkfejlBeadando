package hu.elte.alkfejl.acquire.api;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewRating;
import hu.elte.alkfejl.acquire.service.RatingService;
import hu.elte.alkfejl.acquire.service.SessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//
//GET /api/ratings/available        Shows the pending ratings of the logged in user, one can only rate COMPLETE-d ads
//PUT /api/ratings/available/id     Write a review of the contact between the two parties,
//GET /api/ratings                  Return own ratings
//                                  request a JSON body with NerRating type.


@RestController
@RequestMapping("api/ratings")
public class RatingController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private RatingService ratingService;

    
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping
    private ResponseEntity<List<Rating>> getOwnRatings(){
        return ResponseEntity.ok(ratingService.getOwnRatings(sessionService.getCurrentUser()));
    }
    
    
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/available")
    private ResponseEntity getAvaiableRatings() {
        List<Rating> ratings;
        try{
            ratings = ratingService.getAvailableRating(sessionService.getCurrentUser());
        }catch(Exception ex){
            return ResponseEntity.ok(ex);
        }
        return ResponseEntity.ok(ratings);
  
        
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @PutMapping("/available/{ratingId}")
    private ResponseEntity rate(@PathVariable int ratingId, @RequestBody NewRating rating) {
        boolean successfulRate = ratingService.rate(sessionService.getCurrentUser(), rating, ratingId);
        if (successfulRate) {
            return ResponseEntity.ok(200);
        } else {
            return ResponseEntity.ok(400);
        }
    }

}