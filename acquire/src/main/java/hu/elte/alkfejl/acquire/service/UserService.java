package hu.elte.alkfejl.acquire.service;

import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
        currUser.clone(user);
        return userRepository.save(currUser);
    }
}
