package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    @Autowired
    SessionService sessionService;
    
    @Autowired
    UserRepository userRepository;
    
  
}
