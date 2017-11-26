package hu.elte.alkfejl.acquire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.elte.alkfejl.acquire.model.post.PostUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{
    public User(PostUser newUser){
        username = newUser.getUsername();
        password = newUser.getPassword();
        rating = 0;
        role = Role.USER;
        balance = 0;
    }
    
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private float rating;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private int balance;

    public void addBalance(int balance){
        this.balance+=balance;
    }

    
    public enum Role{
        ADMIN,USER,GUEST
    }
    
    @JsonIgnore
    @OneToMany(mappedBy = "costumer_id",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ad> ads;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "rater")
    private List<Rating> pendigRatings;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "rated")
    private Rating rating_id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "rater")
    private Rating ratng_id;
    
    public void clone(PostUser u){
        this.username=u.getUsername();
        this.password=u.getPassword();
    }
}
