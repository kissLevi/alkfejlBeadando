package hu.elte.alkfejl.acquire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RATINGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends BaseEntity{
    public Rating(User rater,User rated,RatingType type){
        this.rater = rater;
        this.rated = rated;
        this.type = type;
    }

    @JoinColumn(name="rater_id")
    @OneToOne(targetEntity = User.class)
    private User rater;

    @Column(nullable = false)
    private float rating;

    @Column
    private String description;

    @JsonIgnore
    @JoinColumn(name="rated_id", nullable=false)
    @OneToOne(targetEntity = User.class)
    private User rated;
    
    @Enumerated(EnumType.STRING)
    @Column
    private RatingType type;

    public enum RatingType {
        DELIVER,CUSTOMER
    }
    
    public String getRated(){
        return rated.getUsername();
    }
    public User getRatedUser(){ return rated; }

    @JsonIgnore
    @ManyToOne
    @JoinTable(name="PENDING_RATINGS",
            joinColumns={@JoinColumn(name="rate_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private User pendingUser;
}