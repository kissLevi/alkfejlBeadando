package hu.elte.alkfejl.acquire.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUser {
    private String username;
    private String password;
}
