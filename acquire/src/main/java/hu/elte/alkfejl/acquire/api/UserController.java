
package hu.elte.alkfejl.acquire.api;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;

import java.util.List;
import java.util.Optional;

import hu.elte.alkfejl.acquire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//GET   /api/user/users                List all users

//GET   /api/user/:id             List one user with {id}
//DELETE /api/user/:id            Delete one user with all his other info in tables.
//PUT   /api/user/:id             Update a user, request a PostUser model type body.

//GET   /api/user/ads        List logged user's ads

//GET   /api/user/:id/balance    Show one user's available balance
//POST  /api/user/:id/balance    ADMIN can credit a payment to one user's balance, requests a JSON with one number

//

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private SessionService sessionService;

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/users")
    private ResponseEntity<Iterable<User>> listUsers(){
        Iterable<User> users = userService.listAll();
        return ResponseEntity.ok(users);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{userID}")
    public ResponseEntity getUser(@PathVariable int userID){
        User user = userService.listOne(new Long(userID));
        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/ads")
    public ResponseEntity getAdsOfUser(){
        User user = userService.listOne(this.sessionService.getCurrentUser().getId());
        if(user != null){
            return ResponseEntity.ok(user.getAds());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{userID}/balance")
    public ResponseEntity getUserBalance(@PathVariable int userID){
        User user = userService.listOne(new Long(userID));
        if(user != null){
            return ResponseEntity.ok(user.getBalance());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    //Az Admin tudja feltolteni az adott felhasznalo egyenleget.
    @Role({User.Role.ADMIN})
    @PostMapping("/{userID}/balance")
    public ResponseEntity postUserBalance(@PathVariable int userID, @RequestBody String balance){
        User updated = userService.addBalance(userID,Integer.parseInt(balance));
        return ResponseEntity.ok(updated);
    }

    //admin -> felhasznalo torlese
    //@Role({User.Role.ADMIN})
    @DeleteMapping("/{userID}")
    private ResponseEntity delete(@PathVariable int userID){
        if(userID!=0) {
            userService.delete(userID);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("/{userID}")
    private ResponseEntity update(@PathVariable int userID, @RequestBody PostUser user){
        User updated;
        try{
            updated = userService.update(userID,user);
            sessionService.setCurrentUser(userService.listOne(sessionService.getCurrentUser().getId()));
            return ResponseEntity.ok(updated);
        }
        catch(Exception ex){
            return ResponseEntity.ok(400);
        }   
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @GetMapping("/{userID}/ratings")
    private ResponseEntity<Iterable<Rating>> listRatings(@PathVariable int userID){
        Iterable<Rating> userRatings = userService.listRatings(userID);
        return ResponseEntity.ok(userRatings);
    }
}
