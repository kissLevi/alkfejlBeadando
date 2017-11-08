package hu.elte.alkfejl.acquire.model;

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

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private float rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column
    private int balance;

    public enum Role{
        ADMIN,USER,GUEST
    }

//    @JoinColumn
    @OneToMany(targetEntity = Ad.class,mappedBy = "costumer_id")
    private List<Ad> ads;

    //@JoinColumn
    @OneToMany(targetEntity = Payment.class,mappedBy = "payer_id")
    private List<Payment> payments;

    //@JoinColumn
    @OneToMany(targetEntity = Rating.class,mappedBy = "rated_id")
    private List<Rating> ratings;
}
