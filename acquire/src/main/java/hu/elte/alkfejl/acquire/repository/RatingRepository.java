package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


public interface RatingRepository extends CrudRepository<Rating, Long>{
    Optional<User> findById(Long id);
    
    @Query("select r.id from Rating r join r.rater_id ra")
    List<String> findByIdR();
}
