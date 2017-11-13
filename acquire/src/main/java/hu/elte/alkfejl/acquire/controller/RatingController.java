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
//
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("")
    private ResponseEntity<List<Rating>> postRating(){
        return ResponseEntity.ok(ratingRepository.findByRatedUser(sessionService.getCurrentUser()));
    }
    
    
    @Role({User.Role.ADMIN, User.Role.USER})
    @PatchMapping("/available/{ratingId}")
    private ResponseEntity<List<Rating>> postRating(@PathVariable int ratingId,@RequestBody NewRating rating){
        User user = userRepository.findOne(sessionService.getCurrentUser().getId());
        Optional<Rating> pendingRating = userRepository.getRating(user.getId(),new Long(ratingId));
          if(pendingRating.isPresent()){
              User rated = pendingRating.get().getRatedUser();
              user.getPendigRatings().remove(pendingRating.get());
              pendingRating.get().setRating(rating.getRating());
              pendingRating.get().setDescription(rating.getDescription());
              ratingRepository.save(pendingRating.get());
              float numberOfRatings = ratingRepository.sum(rated);
              rated.setRating((rated.getRating()+rating.getRating())/numberOfRatings);
              userRepository.save(rated);
              return ResponseEntity.ok().build();
          }
          else{
              return ResponseEntity.badRequest().build();
          }
        
        
    }
    
}