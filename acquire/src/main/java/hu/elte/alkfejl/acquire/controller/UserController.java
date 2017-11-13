
package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import java.util.Optional;

import hu.elte.alkfejl.acquire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/users")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }
    
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/users/{userID}")
    public ResponseEntity getUser(@PathVariable int userID){
        Optional<User> user = userRepository.findById(new Long(userID));
        if(user.isPresent()){
            return ResponseEntity
                    .ok(userRepository.findOne(new Long(userID)).toString());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/users/{userID}/ads")
    public ResponseEntity getAdsOfUser(@PathVariable int userID){
        Optional<User> user = userRepository.findById(new Long(userID));
        if(user.isPresent()){
            return ResponseEntity
                    .ok(userRepository.findOne(new Long(userID)).getAds());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
    
    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/users/{userID}/ratings")
    public ResponseEntity getRatingsOfUser(@PathVariable int userID){
        Optional<User> user = userRepository.findById(new Long(userID));
        if(user.isPresent()){
            return ResponseEntity
                    .ok(userRepository.findOne(new Long(userID)).getRatings());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/users/{userID}/balance")
    public ResponseEntity getUserBalance(@PathVariable int userID){
        Optional<User> user = userRepository.findById(new Long(userID));
        if(user.isPresent()){
            return ResponseEntity.ok(userRepository.findOne(new Long(userID)).getBalance());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    //Az Admin tudja feltolteni az adott felhasznalo egyenleget.
    @Role({User.Role.ADMIN})
    @PostMapping("/users/{userID}/balance/add")
    public ResponseEntity postUserBalance(@PathVariable int userID, @RequestBody String balance){
        User updated = userService.addBalance(userID,Integer.parseInt(balance));
        return ResponseEntity.ok(updated);
    }

    //admin felhasznalo torlese
    @Role({User.Role.ADMIN})
    @DeleteMapping("/users/{userID}/")
    private ResponseEntity delete(@PathVariable int userID){
        if(userID!=0) {
            userService.delete(userID);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    //
    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("/users/{userID}")
    private ResponseEntity update(@PathVariable int userID, @RequestBody PostUser user){
        User updated = userService.update(userID,user);
        return ResponseEntity.ok(updated);
    }
}
