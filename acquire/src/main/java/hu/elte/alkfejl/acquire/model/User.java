package hu.elte.alkfejl.acquire.model;

import hu.elte.alkfejl.acquire.model.post.PostUser;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    
    public String toString(){
        return "{"+ "\"username\": \"" + username + "\",\"rating\": " + rating +"}";
    }

//    @JoinColumn
//    @OneToMany(targetEntity = Ad.class,mappedBy = "costumer_id")
    @OneToMany(mappedBy = "costumer_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ad> ads;

//    //@JoinColumn
//    @OneToMany(targetEntity = Payment.class,mappedBy = "payer_id")
//    private List<Payment> payments;
//
//    //@JoinColumn
    @ManyToMany(mappedBy = "rater_id")
    private Set<Rating> pendingRatings = new HashSet<>();
    
    @ManyToMany(mappedBy = "rated_id")
    private Set<Rating> ratings = new HashSet<>();
    
    public void clone(PostUser u){
        this.username=u.getUsername();
        this.password=u.getPassword();
    }
}
