package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.Rating;
import org.springframework.data.repository.CrudRepository;


public interface RatingRepository extends CrudRepository<Rating, Long>{
    
}
