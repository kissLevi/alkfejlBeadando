package hu.elte.alkfejl.acquire.repository;


import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


public interface RatingRepository extends CrudRepository<Rating, Long>{
    @Query("select r from Rating r where r.rated = ?1 and r.status = 'DONE'")  
    List<Rating> findByRatedUser(User rated);
    
    @Query("select count(r) from Rating r where r.rated = ?1 and r.rating != 0")
    int sum(User rated);
    
    @Query("select r from Rating r where r.rater = ?1 and r.status = 'PENDING'")
    Optional<List<Rating>> listPendingRatings(User rater);
   
    @Query("select r from Rating r where r.rater = ?1 and r.id = ?2 and r.status = 'PENDING'")
    Optional<Rating> getPendingRating(User user,Long ratingId);

    //    @Query("select pr from User u join u.pendigRatings pr where u.id = ?1 and pr.id = ?2")
//    Optional<Rating> getRating(Long userId,Long ratingId);
    
//    @Query("select r from Rating r join r.rater_id ra where r.id =?1 and ra.id = ?2")
//    Optional<Rating> findByIdandRaterId(Long id,Long user);
//    
//    @Query("select r from Rating r join r.rater_id ra where r.id =?1 and ra.id = ?2")
//    Optional<Rating> findByIdandRatedId(Long id,Long user);
//    
//    @Query("select r from Rating r join r.rater_id ra where r.status = 'PENDING' and ra.id = ?1 ")
//    List<Rating> findByAvailableRatings(Long id);
//    
//    @Query("select r from Rating r join r.rated_id ra where r.status = 'DONE' and ra.id = ?1 ")
//    List<Rating> findRatings(Long id);
//    
}
