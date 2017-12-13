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
    public Rating(User rater,User rated,RatingType type,RatingStatus status){
        this.rater = rater;
        this.rated = rated;
        this.type = type;
        this.status = status;
    }

    @JsonIgnore
    @JoinColumn(name="rater_id")
    @ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
    private User rater;
   
    @Column(nullable = false)
    private float rating;

    @Column
    private String description;


    @JsonIgnore
    @JoinColumn(name="rated_id", nullable=false)
    @ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
    private User rated;

   
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RatingStatus status;
    
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RatingType type;

    public enum RatingType {
        DELIVER,CUSTOMER
    }
    
    public enum RatingStatus{
        PENDING,DONE
    }
    
    public String getRated(){
        return rated.getUsername();
    }
    
    @JsonIgnore
    public User getRatedUserData(){ return rated; }
    
    public String getRatedUser(){ 
        return rated.getUsername(); 
    }
    
    public String getRaterUser(){
        return rater.getUsername();
    }

}