package hu.elte.alkfejl.acquire.service;

import hu.elte.alkfejl.acquire.model.User;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Data
public class SessionService {
    private User currentUser;
}
