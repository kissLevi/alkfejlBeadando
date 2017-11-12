package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RatingRepository extends CrudRepository<Rating, Long>{
    Optional<User> findById(Long id);
}
