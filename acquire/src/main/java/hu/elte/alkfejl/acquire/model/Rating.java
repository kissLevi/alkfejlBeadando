package hu.elte.alkfejl.acquire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 
import javax.persistence.*;
 
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
    
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="Rater_id", nullable=false)
    private User rater;
    
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="Rated_id", nullable=false)
    private User rated;
    

    @Column(nullable = false)
    private float rating;

    @Column
    private String description;
    
   
    @Enumerated(EnumType.STRING)
    @Column
    private RatingType type;

    public enum RatingType {
        DELIVER,CUSTOMER
    }
    
    public String getRated(){
        return rated.getUsername();
    }
    public User getRatedUser(){
        return rated;
    }
}