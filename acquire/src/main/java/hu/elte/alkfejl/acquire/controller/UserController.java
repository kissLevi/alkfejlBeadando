
package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;
    
    @Role({User.Role.ADMIN, User.Role.GUEST})
    @GetMapping("")
    public String postUser(Model model){
        Iterable<User> list = userRepository.findAll();
        for(User user : list){
            System.out.println(user.getUsername() + user.getRating());
        }
        model.addAttribute("users",list);
        return "user";
    }
}
