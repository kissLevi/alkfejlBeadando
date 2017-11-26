package hu.elte.alkfejl.acquire.api;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
public class LogApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;

    @Role({User.Role.GUEST})
    @PostMapping("/register")
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
    @Role({User.Role.GUEST})
    @GetMapping("/login")
    public ResponseEntity<User> checkLog(){
        if(this.sessionService.getCurrentUser() != null){
           return ResponseEntity.ok(this.sessionService.getCurrentUser()); 
        }
        return ResponseEntity.badRequest().build();
    }
}
