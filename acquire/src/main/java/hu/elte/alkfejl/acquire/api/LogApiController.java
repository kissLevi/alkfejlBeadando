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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
public class LogApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Role({User.Role.GUEST})
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody PostUser user){
        try{
            User newUser = new User(user);
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
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
        Optional<User> login = userRepository.findByUsername(user.getUsername());
        if(login.isPresent() && passwordEncoder.matches(user.getPassword(), login.get().getPassword())){
            sessionService.setCurrentUser(login.get());
            return ResponseEntity.ok(login.get());
        }
        else{
            return ResponseEntity.status(403).build();
        }
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @RequestMapping("/logout")
    public ResponseEntity logout() {
        sessionService.setCurrentUser(null);
        return ResponseEntity.ok(false);
    }
    @Role({User.Role.GUEST})
    @GetMapping("/login")
    public ResponseEntity checkLog(){
        if(this.sessionService.getCurrentUser() != null){
           return ResponseEntity.ok(this.sessionService.getCurrentUser()); 
        }
        return ResponseEntity.ok(false);
    }
}
