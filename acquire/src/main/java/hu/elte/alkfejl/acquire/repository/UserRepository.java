package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    @Query("select u.username as usename, u.rating from User u")
    List<User> listPublicUserData();
    
    
//    @Query("SELECT USERNAME,RATING FROM USER")
//    List<User> listPublicUserData();
}
