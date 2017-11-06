package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;
    
    @Role({User.Role.GUEST})
    @GetMapping("")
    public String getLogin(Model model)
    {
        User loginUser = new User();
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("user",sessionService.getCurrentUser());
        return "login";
    }
    
    @Role({User.Role.GUEST})
    @PostMapping("")
    public String postLogin(@ModelAttribute User loginUser){
        Optional<User> login = userRepository.findByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());
        if(login.isPresent()){
            sessionService.setCurrentUser(login.get());
            return "redirect:/user";
        }
        else{
            return "redirect:/login";
        }
           
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @RequestMapping("/logout")
    public String logout() {
        sessionService.setCurrentUser(null);
        return "redirect:/login";
    }
    
    
}
