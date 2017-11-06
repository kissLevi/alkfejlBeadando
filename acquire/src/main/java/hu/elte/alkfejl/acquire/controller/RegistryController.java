package hu.elte.alkfejl.acquire.controller;


import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistryController {
    @Autowired
    private UserRepository userRepository;
    
    @Role({User.Role.GUEST})
    @GetMapping("")
    public String user(Model model){
        User newUser = new User();
        model.addAttribute("newUser",newUser);
        return "registry";
    }
    
    @Role({User.Role.GUEST})
    @PostMapping("")
    public String registerUser(@ModelAttribute User newUser){
        try{
            userRepository.save(newUser);
        }
        catch(DataIntegrityViolationException ex){
            System.out.println("Hiba! Már szerepel ilyen nevű ember az adatbázisban");
        }
        return "redirect:/register";
    }
    
}
