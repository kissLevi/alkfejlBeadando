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
    
    public List<Rating> getAvailableRating(Long id){
        return userRepository.findOne(id).getPendigRatings();
    }
    
    public boolean rate(User currenUser,NewRating rating,int ratingId){

        Optional<Rating> pendingRating = userRepository.getRating(currenUser.getId(),new Long(ratingId)); 
        
        if(pendingRating.isPresent()){
            Rating newRating = pendingRating.get();
            User rater = newRating.getRater();
            User rated = newRating.getRatedUser();
            
            rater.getPendigRatings().remove(pendingRating.get());
            
            newRating.setRating(rating.getRating());
            newRating.setDescription(rating.getDescription());
            
            rated.setRating(calculateNewRating(rated, rating.getRating()));
            
            ratingRepository.save(newRating);
            userRepository.save(rated);
            return true;
          }
          else{
            return false;
          }
    }
    
    private float calculateNewRating(User rated,float newRatingValue){
        float numberOfRatings = ratingRepository.sum(rated);
        float newRating = ((rated.getRating() * numberOfRatings) + newRatingValue) / (numberOfRatings==0?1:numberOfRatings+1);
        return newRating;
    }

}
