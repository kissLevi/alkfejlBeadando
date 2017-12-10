package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    Optional<User> findByUsername(String username);
    
    
    @Query("select u.username as usename, u.rating from User u")
    List<User> listPublicUserData();
    
    Optional<User> findById(Long id);
    
//    @Query("select pr from User u join u.pendigRatings pr where u.id = ?1 and pr.id = ?2")
//    Optional<Rating> getRating(Long userId,Long ratingId);
//    @Query("SELECT USERNAME,RATING FROM USER")
//    List<User> listPublicUserData();
}
