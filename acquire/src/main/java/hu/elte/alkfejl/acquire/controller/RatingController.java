package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewRating;
import hu.elte.alkfejl.acquire.repository.AdvertisementRepository;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.RatingService;
import hu.elte.alkfejl.acquire.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users/{userID}/rating")
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
    @GetMapping("")
    private ResponseEntity getReview(@PathVariable int userID){
        Optional<User> rating = ratingRepository.findById(new Long(userID));
        if(rating!=null){
            return ResponseEntity.ok(rating);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @PostMapping("")
    private ResponseEntity postRating(@PathVariable int userID, @RequestBody NewRating rating){
        User currentUser = sessionService.getCurrentUser();
        User user = userRepository.findOne(new Long(userID));
        Rating added = ratingService.addRating(user,currentUser,rating);
        return ResponseEntity.ok(added);
    }
}