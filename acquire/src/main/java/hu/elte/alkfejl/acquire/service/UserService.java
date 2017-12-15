package hu.elte.alkfejl.acquire.service;

import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addBalance(int id, int balance){
        User user = userRepository.findOne(new Long(id));
        user.addBalance(balance);
        return userRepository.save(user);
    }

    public void delete (int id){
        userRepository.delete(new Long(id));
    }

    public User update(int id, PostUser user){
        User currUser = userRepository.findOne(new Long(id));
        user.setPassword(user.getPassword().isEmpty()?currUser.getPassword():passwordEncoder.encode(user.getPassword() ));
        currUser.clone(user);
        return userRepository.save(currUser);
    }

    public Iterable<User> listAll(){
        return userRepository.findAll();
    }

    public User listOne(Long id){
        Optional<User> user = userRepository.findById(new Long(id));
        if(user.isPresent()){
            return userRepository.findOne(new Long(id));
        }else{
            return null;
        }
    }

    public Iterable<Rating> listRatings(int id){
        User user = userRepository.findOne(new Long(id));
        return ratingRepository.findByRatedUser(user);
    }
}
