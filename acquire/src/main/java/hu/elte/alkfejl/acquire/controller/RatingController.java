package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewRating;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.RatingService;
import hu.elte.alkfejl.acquire.service.SessionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//
//GET ratings/avilable            visszaadja, hogy a jelenleg bejelentkezett felhsználó kiket tud értékelni
//PATCH ratingns/available/id     adott értékelés megadása
//

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/available")
    private ResponseEntity<List<Rating>> getAvaiableRatings(){
        return ResponseEntity.ok(userRepository.findOne(sessionService.getCurrentUser().getId()).getPendigRatings());

    }


    
    
    @Role({User.Role.ADMIN, User.Role.USER})
    @PatchMapping("/available/{ratingId}")
    private ResponseEntity rate(@PathVariable int ratingId,@RequestBody NewRating rating){
        boolean successfulRate = ratingService.rate(sessionService.getCurrentUser(), rating, ratingId);
        if(successfulRate){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
        
        
        
    }
    
}