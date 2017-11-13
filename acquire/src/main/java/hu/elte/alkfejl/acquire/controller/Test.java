
package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    private RatingRepository ratings;
    
    @Autowired
    private UserRepository usersR;
//    @PostMapping
//    public ResponseEntity<List<Rating>> add(){
//        
//        User u1 = usersR.findOne(new Long(1));
//        User u2 = usersR.findOne(new Long(2));
////        ratings.save(new Rating(u1, u1, 0, "asd"));
////        ratings.save(new Rating(u2, u2, 0, "asd"));
//      
////        ratings.save(new Rating(users, users,Rating.Status.PENDING, 0, "asd"));
////        ratings.save(new Rating(users, users,Rating.Status.PENDING, 0, "asd"));
////        ratings.save(new Rating(users, users,Rating.Status.PENDING, 0, "asd"));
////        ratings.save(new Rating(users, users,Rating.Status.PENDING, 0, "asd"));
////        ratings.save(new Rating(users, users,Rating.Status.PENDING, 0, "asd"));
//
////        u1.getPendingRatings().add(new Rating("asd", 0));
//        //System.out.println(u1.getPendingRatings());
//        
////        System.out.println(ratings.findByRater_id(Rating.Status.PENDING));
////        //System.out.println(usersR.findAll());
////        return ResponseEntity.ok(ratings.findByRater_id(Rating.Status.PENDING));
//        System.out.println(ratings.findByAvailableRatings(u2.getId()));
//        return ResponseEntity.ok(ratings.findByAvailableRatings(u2.getId()));
//    //User u2 = users.findOne(new Long(2));
//    }
}
