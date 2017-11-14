package hu.elte.alkfejl.acquire.service;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewRating;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    
    
//    public Boolean rate(Long id, int UserId){
//        Optional<Rating> rating = ratingRepository.findByIdandRaterId(id,new Long(UserId));
//        if(rating.isPresent()){
//            System.out.println(rating.get());
//            return true;
//        }    
//        else{
//            return false;
//        }
//    }
//    public Rating addRating(User rater, User rated, NewRating newRating){
//        Rating rating = new Rating(rater, rated, newRating);
//        return  ratingRepository.save(rating);
//    }
}