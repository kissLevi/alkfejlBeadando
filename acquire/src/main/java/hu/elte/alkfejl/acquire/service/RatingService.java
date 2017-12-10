package hu.elte.alkfejl.acquire.service;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewRating;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    public List<Rating> getOwnRatings(User user){
        return ratingRepository.findByRatedUser(user);
    }
    
    
    public List<Rating> getAvailableRating(User user) throws Exception{
        Optional<List<Rating>> pendingRatings = this.ratingRepository.listPendingRatings(user);
        if(pendingRatings.isPresent()){
            return pendingRatings.get();
        }
        else{
            throw new Exception("The given user has no pending ratings");
        }
    }
    
    public boolean rate(User currenUser,NewRating rating,int ratingId){

        Optional<Rating> pendingRating = ratingRepository.getPendingRating(currenUser,new Long(ratingId)); 
        
        if(pendingRating.isPresent()){
            Rating newRating = pendingRating.get();
            User rater = newRating.getRater();
            User rated = newRating.getRatedUserData();
            
            
            newRating.setRating(rating.getRating());
            newRating.setDescription(rating.getDescription());
            newRating.setStatus(Rating.RatingStatus.DONE);
            
            rated.setRating(calculateNewRating(rated, rating.getRating(),this.ratingRepository));
            
            ratingRepository.save(newRating);
            userRepository.save(rated);
            return true;
          }
          else{
            return false;
          }
    }
    
    public static float calculateNewRating(User rated,float newRatingValue, RatingRepository ratingRepository){
        float numberOfRatings = ratingRepository.sum(rated);
        float newRating = ((rated.getRating() * numberOfRatings) + newRatingValue) / (numberOfRatings==0?1:numberOfRatings+1);
        return newRating;
    }

}
