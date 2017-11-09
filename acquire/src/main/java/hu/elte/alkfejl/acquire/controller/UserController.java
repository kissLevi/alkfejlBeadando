
package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;
   
    
    @Role({User.Role.GUEST})
    @PostMapping("/registrate")
    public ResponseEntity registerUser(@RequestBody PostUser user){
        try{
            User newUser = new User(user);
            userRepository.save(newUser);
                
            
            
        }
        catch(DataIntegrityViolationException ex){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
        
    }
    @Role({User.Role.GUEST})
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody PostUser user){
        Optional<User> login = userRepository
             .findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(login.isPresent()){
            sessionService.setCurrentUser(login.get());
            //System.out.println(sessionService.getCurrentUser().getAds().get(0));
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @RequestMapping("/logout")
    public ResponseEntity logout() {
        sessionService.setCurrentUser(null);
        return ResponseEntity.ok().build();
    }
    
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
    
    
}
